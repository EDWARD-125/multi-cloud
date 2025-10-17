package com.carcafe.multicloud.model.storage;

/**
 * Implementación concreta de StorageProvisioner para AWS.
 */
public class AWSStorageProvisioner implements StorageProvisioner {
    @Override
    public String createStorage() {
        return "Volumen EBS creado exitosamente en AWS.";
    }
}
