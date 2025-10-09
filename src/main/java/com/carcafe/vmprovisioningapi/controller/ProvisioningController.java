package com.carcafe.vmprovisioningapi.controller;

import com.carcafe.vmprovisioningapi.factory.*;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;
import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {

    @PostMapping("/provision")
    public ResponseEntity<Map<String, Object>> provisionarVM(@RequestBody ProvisionRequest request) {
        try {
            AbstractFactory factory;

            // Selección del proveedor
            switch (request.getProveedor().toLowerCase()) {
                case "aws" -> factory = new AWSFactory();
                case "azure" -> factory = new AzureFactory();
                case "gcp" -> factory = new GCPFactory();
                case "onprem" -> factory = new OnPremFactory();
                default -> throw new IllegalArgumentException("Proveedor no soportado: " + request.getProveedor());
            }

            // Creación de los provisioners
            VMProvisioner vmProv = factory.createVMProvisioner();
            NetworkProvisioner netProv = factory.createNetworkProvisioner();
            StorageProvisioner stoProv = factory.createStorageProvisioner();

            //  Validación cruzada: verificar que todos los recursos son del mismo
            // proveedor
            String proveedor = request.getProveedor().toLowerCase();
            boolean vmOk = vmProv.getClass().getSimpleName().toLowerCase().contains(proveedor);
            boolean netOk = netProv.getClass().getSimpleName().toLowerCase().contains(proveedor);
            boolean stoOk = stoProv.getClass().getSimpleName().toLowerCase().contains(proveedor);

            if (!vmOk || !netOk || !stoOk) {
                throw new IllegalArgumentException(
                        "Incoherencia detectada: los recursos no pertenecen al mismo proveedor ("
                                + request.getProveedor() + ")");
            }

            //  Simular acciones de aprovisionamiento (solo si todo es coherente)
            vmProv.provisionVM(request.getNombreVM(), request.getCpu(), request.getMemoria(),
                    request.getAlmacenamiento());
            netProv.configurarRed("red-" + request.getProveedor());
            stoProv.crearDisco(request.getAlmacenamiento(), true);

            // Construir respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("estado", " Aprovisionada correctamente");
            response.put("proveedor", request.getProveedor());
            response.put("nombreVM", request.getNombreVM());
            response.put("cpu", request.getCpu());
            response.put("memoria", request.getMemoria());
            response.put("almacenamiento", request.getAlmacenamiento());

            Map<String, String> componentes = new HashMap<>();
            componentes.put("vm", "Máquina virtual creada exitosamente");
            componentes.put("network", "Red configurada: red-" + request.getProveedor());
            componentes.put("storage", "Disco creado de " + request.getAlmacenamiento() + " GB (encriptado)");

            response.put("componentes", componentes);

            // Retornar JSON con código HTTP 201 (CREATED)
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            // Error por proveedor no soportado u otros datos inválidos
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", " Error en la solicitud");
            errorResponse.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (Exception e) {
            // Error inesperado del servidor
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", " Error interno del servidor");
            errorResponse.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
