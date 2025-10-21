package com.carcafe.vmprovisioningapi.model;

import java.util.UUID;

/**
 * Implementación del provisionador para entornos On-Premise.
 * Simula el despliegue de infraestructura local.
 */
public class OnPremProvisioner implements VMProvisioner {

    @Override
    public String createNetwork(String name) {
        return "onprem-net-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createStorage(int sizeGB) {
        return "onprem-disk-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createVM(String name, int cpu, int memoryGB, int storageGB) {
        return "onprem-vm-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }
}
