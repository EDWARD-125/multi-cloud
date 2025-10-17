package com.carcafe.multicloud.model.vm;

/**
 * Implementación concreta de VMProvisioner para entornos On-Premise.
 */
public class OnPremProvisioner implements VMProvisioner {
    @Override
    public String createVM() {
        return "Máquina física o virtual creada en entorno On-Premise.";
    }
}
