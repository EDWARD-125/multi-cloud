package com.carcafe.vmprovisioningapi.model;

public class AWSStorageProvisioner implements StorageProvisioner {
    @Override
    public void crearDisco(int sizeGB, boolean encriptado) {
        System.out.println("💾 [AWS] Creando volumen EBS de " + sizeGB + " GB, encriptado=" + encriptado);
        System.out.println("volumeId: vol-" + (int)(Math.random()*10000));
    }
}
