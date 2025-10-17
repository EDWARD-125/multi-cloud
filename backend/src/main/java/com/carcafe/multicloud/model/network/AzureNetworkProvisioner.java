package com.carcafe.multicloud.model.network;

/**
 * Implementación concreta de NetworkProvisioner para Azure.
 */
public class AzureNetworkProvisioner implements NetworkProvisioner {
    @Override
    public String createNetwork() {
        return "Red virtual (VNet) configurada correctamente en Azure.";
    }
}
