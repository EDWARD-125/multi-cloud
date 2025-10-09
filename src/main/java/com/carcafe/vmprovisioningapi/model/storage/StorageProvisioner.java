package com.carcafe.vmprovisioningapi.model.storage;

public interface StorageProvisioner {
    void crearDisco(int sizeGB, boolean encriptado);
}
