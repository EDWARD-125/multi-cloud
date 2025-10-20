// frontend/src/components/forms/GcpForm.jsx
import React from 'react';

export default function GcpForm({ template, onChange }) {
  if (!template) return null;
  const vm = template.vm || {};
  const network = template.network || {};
  const storage = template.storage || {};

  const update = (patch) => onChange({ ...template, vm: { ...vm, ...patch.vm }, network: { ...network, ...patch.network }, storage: { ...storage, ...patch.storage } });

  return (
    <div>
      <h3>GCP - Configuración</h3>
      <label>Nombre:
        <input value={template.name || ''} onChange={e => onChange({ ...template, name: e.target.value })} />
      </label>

      <label>Zona:
        <input value={network.zone || ''} onChange={e => update({ network: { zone: e.target.value } })} />
      </label>

      <label>Machine Type:
        <input value={vm.machineType || ''} onChange={e => update({ vm: { machineType: e.target.value } })} />
      </label>

      <label>Memoria (GB):
        <input type="number" value={vm.memoryGB || ''} onChange={e => update({ vm: { memoryGB: Number(e.target.value || 0) } })} />
      </label>

      <label>Disco (GB):
        <input type="number" value={storage.size || ''} onChange={e => update({ storage: { size: Number(e.target.value || 0) } })} />
      </label>
    </div>
  );
}
