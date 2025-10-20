// frontend/src/components/forms/OnpremForm.jsx
import React from 'react';

export default function OnpremForm({ template, onChange }) {
  if (!template) return null;
  const vm = template.vm || {};
  const network = template.network || {};
  const storage = template.storage || {};

  const update = (patch) => onChange({ ...template, vm: { ...vm, ...patch.vm }, network: { ...network, ...patch.network }, storage: { ...storage, ...patch.storage } });

  return (
    <div>
      <h3>On-Prem - Configuración</h3>
      <label>Nombre:
        <input value={template.name || ''} onChange={e => onChange({ ...template, name: e.target.value })} />
      </label>

      <label>Rack / Zona:
        <input value={network.rack || ''} onChange={e => update({ network: { rack: e.target.value } })} />
      </label>

      <label>Flavor:
        <input value={vm.flavor || ''} onChange={e => update({ vm: { flavor: e.target.value } })} />
      </label>

      <label>vCPUs:
        <input type="number" value={vm.vcpus || ''} onChange={e => update({ vm: { vcpus: Number(e.target.value || 0) } })} />
      </label>

      <label>Storage (GB):
        <input type="number" value={storage.size || ''} onChange={e => update({ storage: { size: Number(e.target.value || 0) } })} />
      </label>
    </div>
  );
}
