// frontend/src/components/ProviderFormFactory.jsx
import React from 'react';
import AwsForm from './forms/AwsForm';
import AzureForm from './forms/AzureForm';
import GcpForm from './forms/GcpForm';
import OnpremForm from './forms/OnpremForm';

export default function ProviderFormFactory({ template, onChange }) {
  const provider = (template && template.provider) ? template.provider.toLowerCase() : 'unknown';
  switch (provider) {
    case 'aws': return <AwsForm template={template} onChange={onChange} />;
    case 'azure': return <AzureForm template={template} onChange={onChange} />;
    case 'gcp': return <GcpForm template={template} onChange={onChange} />;
    case 'onprem': return <OnpremForm template={template} onChange={onChange} />;
    default: return <div>Proveedor no soportado: {provider}</div>;
  }
}
