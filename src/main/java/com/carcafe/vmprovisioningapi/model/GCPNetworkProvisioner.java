package com.carcafe.vmprovisioningapi.model;

public class GCPNetworkProvisioner implements NetworkProvisioner {

    @Override
    public void configurarRed(String nombreRed) {
        // Lógica para configurar una red específica en GCP
        System.out.println("Configurando la red '" + nombreRed + "' en Google Cloud Platform...");
    }
}