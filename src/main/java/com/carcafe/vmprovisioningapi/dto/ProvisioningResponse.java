package com.carcafe.vmprovisioningapi.dto;

import lombok.*;
import java.time.LocalDateTime;

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
    private Detalles detalles;

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Detalles {
        private ResourceInfo red;
        private ResourceInfo almacenamiento;
        private VMInfo vm;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ResourceInfo {
        private String id;
        private String mensaje;
        private String extra;
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class VMInfo {
        private String id;
        private String estado;
    }
}
