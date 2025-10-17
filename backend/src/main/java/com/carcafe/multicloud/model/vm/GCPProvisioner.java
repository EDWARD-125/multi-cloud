package com.carcafe.multicloud.model.vm;

/**
 * Implementación concreta de VMProvisioner para Google Cloud Platform.
 */
public class GCPProvisioner implements VMProvisioner {
    @Override
    public String createVM() {
        return "Instancia Compute Engine creada exitosamente en Google Cloud.";
    }
}
