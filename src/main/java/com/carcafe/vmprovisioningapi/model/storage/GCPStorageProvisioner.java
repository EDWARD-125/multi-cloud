package com.carcafe.vmprovisioningapi.model.storage;

public class GCPStorageProvisioner implements StorageProvisioner {

    @Override
    public void crearDisco(int sizeGB, boolean encriptado) {
        System.out.println("[GCP] Creando disco persistente de " + sizeGB + " GB, encriptado=" + encriptado);
    }

}