package com.carcafe.vmprovisioningapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvisioningRequest {
    @NotBlank
    @Pattern(regexp = "(?i)(aws|azure|gcp|onprem)", message = "Proveedor debe ser aws, azure, gcp o onprem")
    private String proveedor;

    @NotBlank
    private String nombreVM;

    @Min(1) @Max(64)
    private int cpu;

    @Min(1) @Max(512)
    private int memoria;

    @Min(10) @Max(10000)
    private int almacenamiento;
}
