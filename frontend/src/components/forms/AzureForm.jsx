// frontend/src/components/forms/AzureForm.jsx
import React from 'react';

export default function AzureForm({ template, onChange }) {
  if (!template) return null;
  const vm = template.vm || {};
  const network = template.network || {};
  const storage = template.storage || {};

  const update = (patch) => onChange({ ...template, vm: { ...vm, ...patch.vm }, network: { ...network, ...patch.network }, storage: { ...storage, ...patch.storage } });

  return (
    <div>
      <h3>Azure - Configuración</h3>
      <label>Nombre:
        <input value={template.name || ''} onChange={e => onChange({ ...template, name: e.target.value })} />
      </label>

      <label>Región:
        <input value={network.region || ''} onChange={e => update({ network: { region: e.target.value } })} />
      </label>

      <label>SKU / Tamaño:
        <input value={vm.sku || ''} onChange={e => update({ vm: { sku: e.target.value } })} />
      </label>

      <label>vCPUs:
        <input type="number" value={vm.vcpus || ''} onChange={e => update({ vm: { vcpus: Number(e.target.value || 0) } })} />
      </label>

      <label>Almacenamiento (GB):
        <input type="number" value={storage.size || ''} onChange={e => update({ storage: { size: Number(e.target.value || 0) } })} />
      </label>
    </div>
  );
}
