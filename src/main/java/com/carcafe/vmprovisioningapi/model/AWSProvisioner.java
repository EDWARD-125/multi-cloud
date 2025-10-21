package com.carcafe.vmprovisioningapi.model;

import java.util.UUID;

public class AWSProvisioner implements VMProvisioner {

    @Override
    public String createNetwork(String name) {
        // Simulación: retornar id
        return "aws-vpc-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createStorage(int sizeGB) {
        return "aws-vol-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }

    @Override
    public String createVM(String name, int cpu, int memoryGB, int storageGB) {
        return "aws-vm-" + Math.abs(UUID.randomUUID().toString().hashCode());
    }
}
