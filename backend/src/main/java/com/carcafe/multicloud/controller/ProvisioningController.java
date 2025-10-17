package com.carcafe.multicloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carcafe.multicloud.builder.*;
import com.carcafe.multicloud.factory.*;
import com.carcafe.multicloud.prototype.VMPrototype;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal para el aprovisionamiento y clonación de recursos en la nube.
 * Integra los patrones Builder, Abstract Factory y Prototype.
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class ProvisioningController {

    // =======================
    // ✅ ENDPOINT: PROVISIONAR VM
    // =======================
    @PostMapping("/provision")
    public ResponseEntity<Map<String, Object>> provisionarVM(@Valid @RequestBody ProvisionRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validaciones básicas
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

            // Crear el Builder según el proveedor
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

            // Construcción según optimización
            VirtualMachine vm;
            if (request.isMemoryOptimization()) {
                vm = director.constructMemoryOptimizedVM(builder, request.getProveedor());
            } else if (request.isDiskOptimization()) {
                vm = director.constructComputeOptimizedVM(builder, request.getProveedor());
            } else {
                vm = director.constructStandardVM(builder, request.getProveedor());
            }

            // Aplicar datos del request
            vm.setRegion(request.getRegion());
            vm.setIops(request.getIops());
            vm.setEstado("Aprovisionada correctamente");

            // Integración con la Abstract Factory (opcional)
            AbstractFactory factory = switch (provider) {
                case "aws" -> new AWSFactory();
                case "azure" -> new AzureFactory();
                case "gcp" -> new GCPFactory();
                case "onprem" -> new OnPremFactory();
                default -> null;
            };

            if (factory != null) {
                vm.setNetwork(factory.createNetworkProvisioner().createNetwork());
                vm.setDiskType(factory.createStorageProvisioner().createStorage());
                vm.setOs(factory.createVMProvisioner().createVM());
            }

            // Respuesta final
            response.put("estado", vm.getEstado());
            response.put("proveedor", vm.getProvider());
            response.put("nombreVM", request.getNombreVM());
            response.put("cpu", vm.getVcpus());
            response.put("memoria", vm.getMemoryGB());
            response.put("almacenamiento", request.getAlmacenamiento());
            response.put("region", vm.getRegion());
            response.put("network", vm.getNetwork());
            response.put("diskType", vm.getDiskType());
            response.put("so", vm.getOs());
            response.put("iops", vm.getIops());
            response.put("publicIP", vm.isPublicIP());
            response.put("firewallRules", request.getFirewallRules());
            response.put("configuracion", vm);

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

    // =======================
    // 🧬 ENDPOINT: CLONAR VM
    // =======================
    @PostMapping("/clone")
    public ResponseEntity<Map<String, Object>> clonarVM(@Valid @RequestBody ProvisionRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (request.getProveedor() == null || request.getProveedor().isBlank())
                throw new IllegalArgumentException("Debe especificar el proveedor de la VM a clonar.");

            // Simular la obtención de una VM existente
            VirtualMachine vmOriginal = new VirtualMachine.Builder(
                    request.getProveedor(), request.getCpu(), request.getMemoria())
                    .setRegion(request.getRegion())
                    .setFirewallRules(request.getFirewallRules())
                    .setIops(request.getIops())
                    .setPublicIP(request.isPublicIP())
                    .build();

            vmOriginal.setNetwork("vpc-12345");
            vmOriginal.setDiskType("SSD");
            vmOriginal.setOs("Linux Ubuntu 22.04");
            vmOriginal.setEstado("Aprovisionada previamente");

            // Clonación con Prototype
            VirtualMachine vmClonada = (VirtualMachine) vmOriginal.clone();
            vmClonada.setEstado("Clon creada correctamente");

            // Se puede cambiar nombre o región si el usuario lo indica
            if (request.getRegion() != null) {
                vmClonada.setRegion(request.getRegion());
            }

            // Respuesta
            response.put("mensaje", "Clonación exitosa del recurso virtual");
            response.put("vmOriginal", vmOriginal);
            response.put("vmClonada", vmClonada);

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
