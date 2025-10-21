package com.carcafe.vmprovisioningapi.model;

public class GCPStorageProvisioner implements StorageProvisioner {

    @Override
    public void crearDisco(int tamanoGB, boolean ssd) {
        // Lógica para crear un disco en GCP
        String tipo = ssd ? "SSD" : "HDD";
        System.out.println("Creando disco " + tipo + " de " + tamanoGB + "GB en Google Cloud Platform...");
    }
}