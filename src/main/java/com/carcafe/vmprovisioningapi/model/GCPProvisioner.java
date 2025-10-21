package com.carcafe.vmprovisioningapi.model;

import java.util.UUID;

/**
 * Implementación del provisionador para Google Cloud Platform (GCP).
 * Simula la creación de recursos mediante identificadores generados localmente.
 */
public class GCPProvisioner implements VMProvisioner {

    @Override
    public String createNetwork(String name) {
        return "gcp-net-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createStorage(int sizeGB) {
        return "gcp-disk-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createVM(String name, int cpu, int memoryGB, int storageGB) {
        return "gcp-vm-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }
}
