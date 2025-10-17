package com.carcafe.multicloud.model.network;

public class OnPremNetworkProvisioner implements NetworkProvisioner {

    @Override
    public void configurarRed(String nombreRed) {
        // Lógica para configurar una red específica en infraestructura local
        System.out.println("Configurando la red '" + nombreRed + "' en infraestructura local...");
    }
}