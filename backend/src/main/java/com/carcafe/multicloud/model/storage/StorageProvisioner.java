package com.carcafe.multicloud.model.storage;

public interface StorageProvisioner {
    void crearDisco(int sizeGB, boolean encriptado);
}
