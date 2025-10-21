package com.carcafe.vmprovisioningapi.model;

public interface VMProvisioner {
    String createNetwork(String name);
    String createStorage(int sizeGB);
    String createVM(String name, int cpu, int memoryGB, int storageGB);
}
