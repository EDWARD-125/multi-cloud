package com.carcafe.multicloud.model.storage;

public class AzureStorageProvisioner implements StorageProvisioner {
    @Override
    public void crearDisco(int sizeGB, boolean encriptado) {
        System.out.println("[AZURE] Creando disco administrado de " + sizeGB + " GB, encriptado=" + encriptado);
        System.out.println("diskSku: Premium_LRS");
    }

}
