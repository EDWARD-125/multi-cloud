package com.carcafe.multicloud.model.network;

/**
 * Implementación concreta de NetworkProvisioner para On-Premise.
 */
public class OnPremNetworkProvisioner implements NetworkProvisioner {
    @Override
    public String createNetwork() {
        return "Red local configurada correctamente en entorno On-Premise.";
    }
}
