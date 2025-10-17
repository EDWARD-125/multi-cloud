package com.carcafe.multicloud.model.storage;

public class OnPremStorageProvisioner implements StorageProvisioner {

    @Override
    public void crearDisco(int sizeGB, boolean encriptado) {
        System.out.println("[ON-PREM] Creando disco local de " + sizeGB + " GB, encriptado=" + encriptado);
    }

}