package com.carcafe.vmprovisioningapi.service;

import com.carcafe.vmprovisioningapi.dto.ProvisioningRequest;
import com.carcafe.vmprovisioningapi.dto.ProvisioningResponse;
import com.carcafe.vmprovisioningapi.factory.*;
import com.carcafe.vmprovisioningapi.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Servicio que maneja la lógica de aprovisionamiento
 * Utiliza tus factories existentes y convierte los resultados en respuestas estructuradas
 */
@Service
public class ProvisioningService {

    /**
     * Aprovisiona una VM completa con red, almacenamiento y compute
     */
    public ProvisioningResponse aprovisionar(ProvisioningRequest request) {
        // Obtener el factory según el proveedor (tu código original)
        AbstractFactory factory = getFactory(request.getProveedor());

        // Crear los provisioners usando tu Abstract Factory Pattern
        NetworkProvisioner red = factory.createNetworkProvisioner();
        StorageProvisioner disco = factory.createStorageProvisioner();
        VMProvisioner vm = factory.createVMProvisioner();

        String nombreRed = "red-" + request.getNombreVM();

        // Ejecutar el aprovisionamiento (sigue imprimiendo en consola)
        System.out.println("\n🚀 Iniciando aprovisionamiento en " + request.getProveedor().toUpperCase() + "...\n");
        red.configurarRed(nombreRed);
        disco.crearDisco(request.getAlmacenamiento(), true);
        vm.provisionVM(request.getNombreVM(), request.getCpu(), request.getMemoria(), request.getAlmacenamiento());
        System.out.println("✅ Aprovisionamiento completo en " + request.getProveedor().toUpperCase());

        // Construir respuesta estructurada según el proveedor
        ProvisioningResponse.DetallesRecursos detalles = construirDetallesRecursos(
            request.getProveedor(),
            nombreRed,
            request.getAlmacenamiento(),
            request.getNombreVM(),
            request.getCpu(),
            request.getMemoria()
        );

        // Retornar la respuesta JSON estructurada
        return ProvisioningResponse.builder()
                .mensaje("Aprovisionamiento completado exitosamente")
                .proveedor(request.getProveedor().toUpperCase())
                .nombreVM(request.getNombreVM())
                .cpu(request.getCpu())
                .memoria(request.getMemoria())
                .almacenamiento(request.getAlmacenamiento())
                .estado("COMPLETADO")
                .timestamp(LocalDateTime.now())
                .detallesRecursos(detalles)
                .build();
    }

    /**
     * Obtiene el factory apropiado según el proveedor
     * Misma lógica que tenías en tu ProvisioningAPI original
     */
    private AbstractFactory getFactory(String proveedor) {
        return switch (proveedor.toLowerCase()) {
            case "aws" -> new AWSFactory();
            case "azure" -> new AzureFactory();
            case "gcp" -> new GCPFactory();
            case "onprem" -> new OnPremFactory();
            default -> throw new IllegalArgumentException("Proveedor no soportado: " + proveedor);
        };
    }

    /**
     * Construye los detalles de recursos según el proveedor
     * Genera IDs aleatorios simulando la respuesta de cada proveedor cloud
     */
    private ProvisioningResponse.DetallesRecursos construirDetallesRecursos(
            String proveedor,
            String nombreRed,
            int almacenamiento,
            String nombreVM,
            int cpu,
            int memoria
    ) {
        ProvisioningResponse.RedInfo redInfo;
        ProvisioningResponse.AlmacenamientoInfo almacenamientoInfo;

        switch (proveedor.toLowerCase()) {
            case "aws" -> {
                // Detalles específicos de AWS
                redInfo = ProvisioningResponse.RedInfo.builder()
                        .mensaje("Red configurada en AWS")
                        .nombreRed(nombreRed)
                        .vpcId("vpc-" + (int)(Math.random() * 1000))
                        .subnetId("subnet-" + (int)(Math.random() * 1000))
                        .securityGroup("sg-" + (int)(Math.random() * 1000))
                        .build();

                almacenamientoInfo = ProvisioningResponse.AlmacenamientoInfo.builder()
                        .mensaje("Volumen EBS creado")
                        .sizeGB(almacenamiento)
                        .encriptado(true)
                        .volumeId("vol-" + (int)(Math.random() * 10000))
                        .build();
            }
            case "azure" -> {
                // Detalles específicos de Azure
                redInfo = ProvisioningResponse.RedInfo.builder()
                        .mensaje("Red virtual configurada en Azure")
                        .nombreRed(nombreRed)
                        .virtualNetwork("vnet-" + (int)(Math.random() * 1000))
                        .subnetId("subnet-" + (int)(Math.random() * 1000))
                        .networkSecurityGroup("nsg-" + (int)(Math.random() * 1000))
                        .build();

                almacenamientoInfo = ProvisioningResponse.AlmacenamientoInfo.builder()
                        .mensaje("Disco administrado creado")
                        .sizeGB(almacenamiento)
                        .encriptado(true)
                        .diskSku("Premium_LRS")
                        .managedDisk(true)
                        .build();
            }
            case "gcp" -> {
                // Detalles específicos de GCP
                redInfo = ProvisioningResponse.RedInfo.builder()
                        .mensaje("Red configurada en GCP")
                        .nombreRed(nombreRed)
                        .build();

                almacenamientoInfo = ProvisioningResponse.AlmacenamientoInfo.builder()
                        .mensaje("Disco creado en GCP")
                        .sizeGB(almacenamiento)
                        .encriptado(true)
                        .tipo("SSD")
                        .build();
            }
            case "onprem" -> {
                // Detalles específicos de On-Premise
                redInfo = ProvisioningResponse.RedInfo.builder()
                        .mensaje("Red configurada en infraestructura local")
                        .nombreRed(nombreRed)
                        .build();

                almacenamientoInfo = ProvisioningResponse.AlmacenamientoInfo.builder()
                        .mensaje("Disco creado en infraestructura local")
                        .sizeGB(almacenamiento)
                        .encriptado(true)
                        .tipo("SSD")
                        .build();
            }
            default -> throw new IllegalArgumentException("Proveedor no soportado");
        }

        // Información de la VM (común para todos los proveedores)
        ProvisioningResponse.VMInfo vmInfo = ProvisioningResponse.VMInfo.builder()
                .mensaje("Máquina virtual creada exitosamente")
                .nombreVM(nombreVM)
                .cpu(cpu)
                .memoria(memoria)
                .almacenamiento(almacenamiento)
                .estado("RUNNING")
                .build();

        // Retornar todos los detalles agrupados
        return ProvisioningResponse.DetallesRecursos.builder()
                .red(redInfo)
                .almacenamiento(almacenamientoInfo)
                .vm(vmInfo)
                .build();
    }
}
