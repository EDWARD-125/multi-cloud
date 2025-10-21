package com.carcafe.vmprovisioningapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para la respuesta del aprovisionamiento de VM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvisioningResponse {
    
    private String mensaje;
    private String proveedor;
    private String nombreVM;
    private int cpu;
    private int memoria;
    private int almacenamiento;
    private String estado;
    private LocalDateTime timestamp;
    private DetallesRecursos detallesRecursos;
    
    /**
     * Clase interna para los detalles de todos los recursos
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetallesRecursos {
        private RedInfo red;
        private AlmacenamientoInfo almacenamiento;
        private VMInfo vm;
    }
    
    /**
     * Información de la red configurada
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RedInfo {
        private String mensaje;
        private String nombreRed;
        // Campos específicos de AWS
        private String vpcId;
        private String subnetId;
        private String securityGroup;
        // Campos específicos de Azure
        private String virtualNetwork;
        private String networkSecurityGroup;
    }
    
    /**
     * Información del almacenamiento creado
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlmacenamientoInfo {
        private String mensaje;
        private int sizeGB;
        private boolean encriptado;
        // Campos específicos de AWS
        private String volumeId;
        // Campos específicos de Azure
        private String diskSku;
        private Boolean managedDisk;
        // Campos específicos de GCP y OnPrem
        private String tipo; // SSD o HDD
    }
    
    /**
     * Información de la VM creada
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VMInfo {
        private String mensaje;
        private String nombreVM;
        private int cpu;
        private int memoria;
        private int almacenamiento;
        private String estado;
    }
}