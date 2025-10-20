// frontend/src/pages/TemplateEditor.jsx
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useTemplateRegistry } from "../contexts/TemplateRegistry";
import ProviderFormFactory from "../components/ProviderFormFactory";
import { applyPreset } from "../utils/presets";
import axios from "axios";
import { safePrepareForSend } from "../utils/helpers";

const API_BASE = import.meta.env.VITE_API_URL || "http://localhost:8080/api/v1";

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
      registry.refreshFromServer().then(() => {
        const t2 = registry.get(id);
        if (t2) setTemplate(t2);
        else setTemplate(null);
      });
    }
  }, [id]); // eslint-disable-line

  if (!template)
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-gray-500 text-lg">
          No se encontró la plantilla (id: {id})
        </p>
      </div>
    );

  const handleChange = (newTemplate) => {
    setTemplate(newTemplate);
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
      const resp = await api.post("/provision", payload);
      alert(`Solicitud enviada. ProvisionId: ${resp.data?.id || "—"}`);
      setSaving(false);
      navigate("/");
    } catch (e) {
      console.error("Provision error", e);
      alert("Error al provisionar: " + (e?.message || "unknown"));
      setSaving(false);
    }
  };

  return (
    <div className="flex justify-center py-10 bg-gray-50 min-h-screen">
      <div className="bg-white shadow-lg rounded-2xl p-8 w-full max-w-4xl">
        <h2 className="text-2xl font-bold text-blue-600 mb-4">
          🧩 Editor de Plantilla
        </h2>
        <p className="text-gray-700 mb-6">
          <strong>{template.name}</strong> —{" "}
          <span className="uppercase">{template.provider}</span>
        </p>

        {/* Presets */}
        <div className="flex flex-wrap gap-3 mb-6">
          <button
            onClick={() => handleApplyPreset("standard")}
            className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg transition-all"
          >
            Preset: Standard
          </button>
          <button
            onClick={() => handleApplyPreset("memoryOptimized")}
            className="bg-purple-500 hover:bg-purple-600 text-white px-4 py-2 rounded-lg transition-all"
          >
            Memory-Optimized
          </button>
          <button
            onClick={() => handleApplyPreset("diskOptimized")}
            className="bg-amber-500 hover:bg-amber-600 text-white px-4 py-2 rounded-lg transition-all"
          >
            Disk-Optimized
          </button>
        </div>

        {/* Formulario dinámico */}
        <div className="border border-gray-200 rounded-xl p-6 mb-6">
          <ProviderFormFactory template={template} onChange={handleChange} />
        </div>

        {/* Acciones */}
        <div className="flex gap-3 justify-end">
          <button
            onClick={() => registry.register(template)}
            className="bg-green-500 hover:bg-green-600 text-white px-5 py-2 rounded-lg transition-all"
          >
            Guardar localmente
          </button>
          <button
            onClick={handleProvision}
            disabled={saving}
            className={`px-5 py-2 rounded-lg text-white transition-all ${
              saving
                ? "bg-blue-300 cursor-not-allowed"
                : "bg-blue-600 hover:bg-blue-700"
            }`}
          >
            {saving ? "Enviando..." : "Provisionar"}
          </button>
          <button
            onClick={() => navigate(-1)}
            className="bg-gray-400 hover:bg-gray-500 text-white px-5 py-2 rounded-lg transition-all"
          >
            Volver
          </button>
        </div>
      </div>
    </div>
  );
}
