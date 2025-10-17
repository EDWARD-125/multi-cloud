package com.carcafe.multicloud.model.vm;

/**
 * Implementación concreta de VMProvisioner para AWS.
 */
public class AWSProvisioner implements VMProvisioner {
    @Override
    public String createVM() {
        return "Instancia EC2 creada exitosamente en AWS.";
    }
}
