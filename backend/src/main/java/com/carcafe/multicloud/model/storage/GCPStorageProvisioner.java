package com.carcafe.multicloud.model.storage;

/**
 * Implementación concreta de StorageProvisioner para Google Cloud Platform.
 */
public class GCPStorageProvisioner implements StorageProvisioner {
    @Override
    public String createStorage() {
        return "Disco persistente (Persistent Disk) creado en Google Cloud.";
    }
}
