package com.carcafe.multicloud.model.network;

/**
 * Implementación concreta de NetworkProvisioner para Google Cloud Platform.
 */
public class GCPNetworkProvisioner implements NetworkProvisioner {
    @Override
    public String createNetwork() {
        return "Red VPC configurada correctamente en Google Cloud.";
    }
}
