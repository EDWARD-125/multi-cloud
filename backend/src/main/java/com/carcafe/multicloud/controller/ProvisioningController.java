package com.carcafe.multicloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carcafe.multicloud.builder.*;
import com.carcafe.multicloud.factory.*;
import com.carcafe.multicloud.service.VirtualMachineService;
import com.carcafe.multicloud.builder.VirtualMachine;
import com.carcafe.multicloud.controller.ProvisionRequest;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal para el aprovisionamiento y clonación de recursos en la nube.
 * Integra los patrones Builder, Abstract Factory, Prototype y la persistencia JPA.
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5174") // habilita peticiones desde el frontend (puerto Vite)
public class ProvisioningController {

    @Autowired
    private VirtualMachineService vmService;

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

            // Construcción según tipo de optimización
            VirtualMachine vm;
            if (request.isMemoryOptimization()) {
                vm = director.constructMemoryOptimizedVM(builder, request.getProveedor());
            } else if (request.isDiskOptimization()) {
                vm = director.constructComputeOptimizedVM(builder, request.getProveedor());
            } else {
                vm = director.constructStandardVM(builder, request.getProveedor());
            }

            // 🔧 Asignar valores faltantes (importante para mostrar en el frontend)
            vm.setVcpus(request.getCpu());
            vm.setMemoryGB(request.getMemoria());
            vm.setDiskType(request.getAlmacenamiento() + " GB");

            // Aplicar propiedades adicionales
            vm.setRegion(request.getRegion());
            vm.setIops(request.getIops());
            vm.setFirewallRules(request.getFirewallRules());
            vm.setEstado("Aprovisionada correctamente");

            // Integración con la Abstract Factory
            AbstractFactory factory = switch (provider) {
                case "aws" -> new AWSFactory();
                case "azure" -> new AzureFactory();
                case "gcp" -> new GCPFactory();
                case "onprem" -> new OnPremFactory();
                default -> null;
            };

            if (factory != null) {
                vm.setNetwork(factory.createNetworkProvisioner().createNetwork());
                vm.setOs(factory.createVMProvisioner().createVM());
            }

            // Guardar en BD
            VirtualMachine savedVM = vmService.save(vm);

            // Respuesta limpia y ordenada
            response.put("mensaje", "Máquina virtual aprovisionada correctamente");
            response.put("vmId", savedVM.getId());
            response.put("estado", savedVM.getEstado());
            response.put("proveedor", savedVM.getProvider());
            response.put("region", savedVM.getRegion());
            response.put("configuracion", savedVM);

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

            // Simula una VM base
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

            // Clonación usando Prototype
            VirtualMachine vmClonada = (VirtualMachine) vmOriginal.clone();
            vmClonada.setEstado("Clon creada correctamente");

            // Guardar clon
            VirtualMachine savedClone = vmService.save(vmClonada);

            response.put("mensaje", "Clonación exitosa");
            response.put("vmClonada", savedClone);

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
