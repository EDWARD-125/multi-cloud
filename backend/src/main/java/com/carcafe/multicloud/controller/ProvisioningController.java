package com.carcafe.multicloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carcafe.multicloud.builder.*;
import com.carcafe.multicloud.factory.*;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal para el aprovisionamiento de recursos en la nube.
 * Usa el patrón Builder y Abstract Factory para construir y aprovisionar máquinas virtuales según el proveedor.
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class ProvisioningController {

    @PostMapping("/provision")
    public ResponseEntity<Map<String, Object>> provisionarVM(@Valid @RequestBody ProvisionRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // ✅ Validaciones básicas
            if (request.getProveedor() == null || request.getProveedor().isBlank())
                throw new IllegalArgumentException("El campo 'proveedor' es obligatorio.");
            if (request.getNombreVM() == null || request.getNombreVM().isBlank())
                throw new IllegalArgumentException("El campo 'nombreVM' es obligatorio.");
            if (request.getCpu() <= 0)
                throw new IllegalArgumentException("El número de CPU debe ser mayor que 0.");
            if (request.getMemoria() <= 0)
                throw new IllegalArgumentException("La memoria debe ser mayor que 0 GB.");
            if (request.getAlmacenamiento() <= 0)
                throw new IllegalArgumentException("El almacenamiento debe ser mayor que 0 GB.");

            // ✅ Crear Builder según proveedor
            VMBuilder builder;
            VMDirector director = new VMDirector();
            String provider = request.getProveedor().toLowerCase();

            switch (provider) {
                case "aws" -> builder = new AWSVMBuilder(request.getCpu(), request.getMemoria());
                case "azure" -> builder = new AzureVMBuilder(request.getCpu(), request.getMemoria());
                case "gcp" -> builder = new GCPVMBuilder(request.getCpu(), request.getMemoria());
                case "onprem" -> builder = new OnPremVMBuilder(request.getCpu(), request.getMemoria());
                default -> throw new IllegalArgumentException("Proveedor no soportado: " + request.getProveedor());
            }

            // ✅ Construir VM según el tipo de optimización
            VirtualMachine vm;
            if (request.isMemoryOptimization()) {
                vm = director.constructMemoryOptimizedVM(builder, request.getProveedor());
            } else if (request.isDiskOptimization()) {
                vm = director.constructComputeOptimizedVM(builder, request.getProveedor());
            } else {
                vm = director.constructStandardVM(builder, request.getProveedor());
            }

            // ✅ Integrar Abstract Factory (creación de red, almacenamiento y SO)
            AbstractFactory factory = FactoryProvider.getFactory(request.getProveedor());
            VMProvisioner vmProv = factory.createVMProvisioner();
            NetworkProvisioner netProv = factory.createNetworkProvisioner();
            StorageProvisioner storProv = factory.createStorageProvisioner();

            String vmResult = vmProv.provisionVM(vm);
            String network = netProv.createNetwork();
            String storage = storProv.createStorage();

            // ✅ Armar respuesta enriquecida
            response.put("estado", "Aprovisionada correctamente");
            response.put("proveedor", request.getProveedor());
            response.put("nombreVM", request.getNombreVM());
            response.put("cpu", request.getCpu());
            response.put("memoria", request.getMemoria());
            response.put("almacenamiento", request.getAlmacenamiento());
            response.put("region", request.getRegion());
            response.put("network", network);
            response.put("storage", storage);
            response.put("so", vmProv.getOS());
            response.put("firewallRules", request.getFirewallRules());
            response.put("iops", request.getIops());
            response.put("detalle", vmResult);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            response.put("error", "Error en la solicitud");
            response.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            response.put("error", "Error interno del servidor");
            response.put("detalle", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
