package com.carcafe.vmprovisioningapi.model;

public class OnPremStorageProvisioner implements StorageProvisioner {

    @Override
    public void crearDisco(int tamanoGB, boolean ssd) {
        // Lógica para crear un disco en infraestructura local
        String tipo = ssd ? "SSD" : "HDD";
        System.out.println("Creando disco " + tipo + " de " + tamanoGB + "GB en infraestructura local...");
    }
}