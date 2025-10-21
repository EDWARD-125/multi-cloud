package com.carcafe.vmprovisioningapi.controller;

import com.carcafe.vmprovisioningapi.dto.ProvisioningRequest;
import com.carcafe.vmprovisioningapi.dto.ProvisioningResponse;
import com.carcafe.vmprovisioningapi.service.ProvisioningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller para el aprovisionamiento de VMs multi-cloud
 * Reemplaza la clase ProvisioningAPI que imprimía en consola
 */
@RestController
@RequestMapping("/api/v1/provisioning")
@RequiredArgsConstructor
@Tag(name = "Provisioning", description = "API para aprovisionamiento de máquinas virtuales multi-cloud")
public class ProvisioningController {

    private final ProvisioningService provisioningService;

    /**
     * Aprovisiona una máquina virtual en el proveedor especificado
     * 
     * @param request Datos de la VM a aprovisionar
     * @return Respuesta con los detalles del aprovisionamiento
     */
    @PostMapping("/vm")
    @Operation(
        summary = "Aprovisionar VM",
        description = "Crea una máquina virtual con red y almacenamiento en el proveedor cloud especificado"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "VM aprovisionada exitosamente",
            content = @Content(schema = @Schema(implementation = ProvisioningResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Solicitud inválida - parámetros incorrectos"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<ProvisioningResponse> aprovisionarVM(
            @Valid @RequestBody ProvisioningRequest request
    ) {
        try {
            ProvisioningResponse response = provisioningService.aprovisionar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // Si el proveedor no es válido, devolver 400
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Health check del servicio
     */
    @GetMapping("/health")
    @Operation(summary = "Health Check", description = "Verifica el estado del servicio")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "VM Provisioning API");
        health.put("version", "1.0.0");
        return ResponseEntity.ok(health);
    }

    /**
     * Obtiene los proveedores soportados
     */
    @GetMapping("/providers")
    @Operation(summary = "Listar proveedores", description = "Obtiene la lista de proveedores cloud soportados")
    public ResponseEntity<Map<String, Object>> getProviders() {
        Map<String, Object> response = new HashMap<>();
        response.put("proveedores", new String[]{"AWS", "Azure", "GCP", "OnPrem"});
        response.put("total", 4);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de ejemplo con valores por defecto
     */
    @GetMapping("/example")
    @Operation(summary = "Ejemplo de request", description = "Muestra un ejemplo de solicitud de aprovisionamiento")
    public ResponseEntity<ProvisioningRequest> getExample() {
        ProvisioningRequest example = ProvisioningRequest.builder()
                .proveedor("aws")
                .nombreVM("mi-servidor-web")
                .cpu(4)
                .memoria(16)
                .almacenamiento(100)
                .build();
        return ResponseEntity.ok(example);
    }

    /**
     * Manejo de excepciones de validación
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Solicitud inválida");
        error.put("mensaje", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Manejo de excepciones generales
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno del servidor");
        error.put("mensaje", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
