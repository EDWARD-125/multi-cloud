package com.carcafe.multicloud.model.storage;

/**
 * Implementación concreta de StorageProvisioner para On-Premise.
 */
public class OnPremStorageProvisioner implements StorageProvisioner {
    @Override
    public String createStorage() {
        return "Disco o volumen local configurado exitosamente en entorno On-Premise.";
    }
}
