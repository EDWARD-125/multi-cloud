package com.carcafe.multicloud.model.network;

/**
 * Implementación concreta de NetworkProvisioner para AWS.
 */
public class AWSNetworkProvisioner implements NetworkProvisioner {
    @Override
    public String createNetwork() {
        return "Red VPC configurada correctamente en AWS.";
    }
}
