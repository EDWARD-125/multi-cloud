// frontend/src/components/forms/AwsForm.jsx
import React from 'react';

export default function AwsForm({ template, onChange }) {
  if (!template) return null;
  const vm = template.vm || {};
  const network = template.network || {};
  const storage = template.storage || {};

  const update = (patch) => onChange({ ...template, vm: { ...vm, ...patch.vm }, network: { ...network, ...patch.network }, storage: { ...storage, ...patch.storage } });

  return (
    <div>
      <h3>AWS - Configuración</h3>
      <label>Nombre:
        <input value={template.name || ''} onChange={e => onChange({ ...template, name: e.target.value })} />
      </label>

      <div>
        <label>Región:
          <input value={network.region || ''} onChange={e => update({ network: { region: e.target.value } })} />
        </label>

        <label>Instance Type:
          <input value={vm.instanceType || ''} onChange={e => update({ vm: { instanceType: e.target.value } })} />
        </label>

        <label>vCPUs:
          <input type="number" value={vm.vcpus || ''} onChange={e => update({ vm: { vcpus: Number(e.target.value || 0) } })} />
        </label>

        <label>Mem (GB):
          <input type="number" value={vm.memoryGB || ''} onChange={e => update({ vm: { memoryGB: Number(e.target.value || 0) } })} />
        </label>

        <label>Storage (GB):
          <input type="number" value={storage.size || ''} onChange={e => update({ storage: { size: Number(e.target.value || 0) } })} />
        </label>
      </div>
    </div>
  );
}
