package com.carcafe.multicloud.model.vm;

/**
 * Interfaz para crear máquinas virtuales (parte del patrón Abstract Factory).
 */
public interface VMProvisioner {
    String createVM();
}
