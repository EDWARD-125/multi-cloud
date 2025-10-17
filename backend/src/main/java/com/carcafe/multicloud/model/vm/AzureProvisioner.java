package com.carcafe.multicloud.model.vm;

public class AzureProvisioner implements VMProvisioner {

    @Override
    public void provisionVM(String nombreVM, int cpu, int memoria, int almacenamiento) {
        System.out.println("Aprovisionando máquina virtual en Microsoft Azure...");
        System.out.println("Nombre: " + nombreVM);
        System.out.println("CPU: " + cpu + " núcleos");
        System.out.println("Memoria: " + memoria + " GB");
        System.out.println("Almacenamiento: " + almacenamiento + " GB");
        System.out.println(" Máquina creada exitosamente en Azure.");
    }
}
