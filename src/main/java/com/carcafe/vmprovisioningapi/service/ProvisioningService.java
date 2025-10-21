package com.carcafe.vmprovisioningapi.service;

import com.carcafe.vmprovisioningapi.dto.*;
import com.carcafe.vmprovisioningapi.factory.*;
import com.carcafe.vmprovisioningapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ProvisioningService {

    private final FactoryProvider factoryProvider;

    public ProvisioningResponse provision(ProvisioningRequest req) {
        VMFactory factory = factoryProvider.getFactory(req.getProveedor().toLowerCase());
        if (factory == null) {
            throw new IllegalArgumentException("Proveedor no soportado: " + req.getProveedor());
        }

        // Factory Method: create provisioner
        VMProvisioner provisioner = factory.createVMProvisioner();

        // Create resources using provisioner methods (these are synchronous/simulated)
        String networkId = provisioner.createNetwork("red-" + req.getNombreVM());
        String diskId = provisioner.createStorage(req.getAlmacenamiento());
        String vmId = provisioner.createVM(req.getNombreVM(), req.getCpu(), req.getMemoria(), req.getAlmacenamiento());

        ProvisioningResponse.Detalles detalles = ProvisioningResponse.Detalles.builder()
            .red(ProvisioningResponse.ResourceInfo.builder()
                    .id(networkId).mensaje("Red creada").extra("provider="+req.getProveedor()).build())
            .almacenamiento(ProvisioningResponse.ResourceInfo.builder()
                    .id(diskId).mensaje("Disco creado").extra("sizeGB="+req.getAlmacenamiento()).build())
            .vm(ProvisioningResponse.VMInfo.builder()
                    .id(vmId).estado("RUNNING").build())
            .build();

        return ProvisioningResponse.builder()
                .mensaje("Aprovisionamiento completado")
                .proveedor(req.getProveedor().toUpperCase())
                .nombreVM(req.getNombreVM())
                .cpu(req.getCpu())
                .memoria(req.getMemoria())
                .almacenamiento(req.getAlmacenamiento())
                .estado("COMPLETADO")
                .timestamp(LocalDateTime.now())
                .detalles(detalles)
                .build();
    }
}
