package com.carcafe.multicloud.model.storage;

/**
 * Implementación concreta de StorageProvisioner para Azure.
 */
public class AzureStorageProvisioner implements StorageProvisioner {
    @Override
    public String createStorage() {
        return "Disco administrado (Managed Disk) creado exitosamente en Azure.";
    }
}
