package com.carcafe.vmprovisioningapi.controller;

import com.carcafe.vmprovisioningapi.factory.*;
import com.carcafe.vmprovisioningapi.model.*;

public class ProvisioningAPI {

    public void aprovisionar(String proveedor, String nombreVM, int cpu, int memoria, int almacenamiento) {
        AbstractFactory factory;

        switch (proveedor.toLowerCase()) {
            case "aws" -> factory = new AWSFactory();
            case "azure" -> factory = new AzureFactory();
            case "gcp" -> factory = new GCPFactory();
            case "onprem" -> factory = new OnPremFactory();
            default -> throw new IllegalArgumentException("Proveedor no soportado: " + proveedor);
        }

        NetworkProvisioner red = factory.createNetworkProvisioner();
        StorageProvisioner disco = factory.createStorageProvisioner();
        VMProvisioner vm = factory.createVMProvisioner();

        System.out.println("\n🚀 Iniciando aprovisionamiento en " + proveedor.toUpperCase() + "...\n");

        red.configurarRed("red-" + nombreVM);
        disco.crearDisco(almacenamiento, true);
        vm.provisionVM(nombreVM, cpu, memoria, almacenamiento);

        System.out.println("✅ Aprovisionamiento completo en " + proveedor.toUpperCase());
    }
}
