package com.carcafe.multicloud.model.vm;

/**
 * Implementación concreta de VMProvisioner para Microsoft Azure.
 */
public class AzureProvisioner implements VMProvisioner {
    @Override
    public String createVM() {
        return "Máquina virtual creada exitosamente en Azure (Azure VM).";
    }
}
