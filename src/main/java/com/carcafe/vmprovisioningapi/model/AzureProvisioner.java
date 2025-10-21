package com.carcafe.vmprovisioningapi.model;

import java.util.UUID;

/**
 * Implementación del provisionador para Azure.
 * Genera identificadores únicos simulados para red, disco y VM.
 */
public class AzureProvisioner implements VMProvisioner {

    @Override
    public String createNetwork(String name) {
        return "az-net-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createStorage(int sizeGB) {
        return "az-disk-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createVM(String name, int cpu, int memoryGB, int storageGB) {
        return "az-vm-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }
}
