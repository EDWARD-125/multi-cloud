package com.carcafe.vmprovisioningapi.controller;

import com.carcafe.vmprovisioningapi.factory.*;
import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public class ProvisioningAPI {

    public void provisionar(String proveedor, String nombreVM, int cpu, int memoria, int almacenamiento) {
        VMFactory factory;

switch (proveedor.toLowerCase()) {
    case "aws":
        factory = new AWSFactory();
        break;
    case "azure":
        factory = new AzureFactory();
        break;
    case "gcp":
        factory = new GCPFactory();
        break;
    case "onprem":
    case "local":
        factory = new OnPremFactory();
        break;
    default:
        throw new IllegalArgumentException("Proveedor no soportado: " + proveedor);
}


        VMProvisioner provisioner = factory.createProvisioner();
        provisioner.provisionVM(nombreVM, cpu, memoria, almacenamiento);
    }
}
