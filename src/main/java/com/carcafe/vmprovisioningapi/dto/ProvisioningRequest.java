package com.carcafe.vmprovisioningapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la solicitud de aprovisionamiento de VM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvisioningRequest {
    
    @NotBlank(message = "El proveedor es obligatorio")
    @Pattern(
        regexp = "(?i)(aws|azure|gcp|onprem)",
        message = "Proveedor debe ser: aws, azure, gcp o onprem"
    )
    private String proveedor;
    
    @NotBlank(message = "El nombre de la VM es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombreVM;
    
    @Min(value = 1, message = "CPU debe ser al menos 1")
    @Max(value = 64, message = "CPU no puede exceder 64")
    private int cpu;
    
    @Min(value = 1, message = "Memoria debe ser al menos 1 GB")
    @Max(value = 512, message = "Memoria no puede exceder 512 GB")
    private int memoria;
    
    @Min(value = 10, message = "Almacenamiento debe ser al menos 10 GB")
    @Max(value = 10000, message = "Almacenamiento no puede exceder 10000 GB")
    private int almacenamiento;
}