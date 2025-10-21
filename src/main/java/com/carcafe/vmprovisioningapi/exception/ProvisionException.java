package com.carcafe.vmprovisioningapi.exception;

/**
 * Excepción personalizada para errores durante el aprovisionamiento.
 * Puede ser lanzada por los servicios o factories en caso de fallos simulados.
 */
public class ProvisionException extends RuntimeException {

    public ProvisionException(String message) {
        super(message);
    }

    public ProvisionException(String message, Throwable cause) {
        super(message, cause);
    }
}
