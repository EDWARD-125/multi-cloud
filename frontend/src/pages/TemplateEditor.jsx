// frontend/src/pages/TemplateEditor.jsx
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useTemplateRegistry } from '../contexts/TemplateRegistry';
import ProviderFormFactory from '../components/ProviderFormFactory';
import { applyPreset } from '../utils/presets';
import axios from 'axios';
import { safePrepareForSend } from '../utils/helpers';

const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1';

export default function TemplateEditor() {
  const { id } = useParams();
  const navigate = useNavigate();
  const registry = useTemplateRegistry();
  const [template, setTemplate] = useState(null);
  const [saving, setSaving] = useState(false);
  const api = axios.create({ baseURL: API_BASE });

  useEffect(() => {
    const t = registry.get(id);
    if (t) setTemplate(t);
    else {
      // try refresh from server then get
      registry.refreshFromServer().then(() => {
        const t2 = registry.get(id);
        if (t2) setTemplate(t2);
        else setTemplate(null);
      });
    }
  }, [id]); // eslint-disable-line

  if (!template) return <div>No se encontró la plantilla (id: {id})</div>;

  const handleChange = (newTemplate) => {
    setTemplate(newTemplate);
    // update registry copy
    registry.register(newTemplate);
  };

  const handleApplyPreset = (presetName) => {
    const newT = applyPreset(template, presetName);
    handleChange(newT);
  };

  const handleProvision = async () => {
    setSaving(true);
    try {
      const payload = safePrepareForSend(template);
      const resp = await api.post('/provision', payload);
      // show id / estado
      alert(`Solicitud enviada. ProvisionId: ${resp.data?.id || '—'}`);
      setSaving(false);
      navigate('/');
    } catch (e) {
      console.error('Provision error', e);
      alert('Error al provisionar: ' + (e?.message || 'unknown'));
      setSaving(false);
    }
  };

  return (
    <div>
      <h2>Editor de Plantilla</h2>
      <div style={{ marginBottom: 12 }}>
        <strong>{template.name}</strong> — {template.provider}
      </div>

      <div style={{ marginBottom: 8 }}>
        <button onClick={() => handleApplyPreset('standard')}>Aplicar Preset: Standard</button>
        <button onClick={() => handleApplyPreset('memoryOptimized')} style={{ marginLeft: 6 }}>Memory-Optimized</button>
        <button onClick={() => handleApplyPreset('diskOptimized')} style={{ marginLeft: 6 }}>Disk-Optimized</button>
      </div>

      <ProviderFormFactory template={template} onChange={handleChange} />

      <div style={{ marginTop: 12 }}>
        <button onClick={() => registry.register(template)}>Guardar localmente</button>
        <button onClick={handleProvision} disabled={saving} style={{ marginLeft: 8 }}>{saving ? 'Enviando...' : 'Provisionar'}</button>
        <button onClick={() => navigate(-1)} style={{ marginLeft: 8 }}>Volver</button>
      </div>
    </div>
  );
}
