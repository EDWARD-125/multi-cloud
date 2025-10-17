package com.carcafe.multicloud.builder;

public class VMDirector {

    //  Construcción de VM Standard (General Purpose)
    public VirtualMachine constructStandardVM(VMBuilder builder, String provider) {
        int[] specs = getSpecs(provider, "standard");
        System.out.println("Construyendo VM estándar para " + provider + " con " + specs[0] + " vCPU y " + specs[1] + " GB RAM");
        return buildVM(builder, specs[0], specs[1], "standard");
    }

    //  Construcción de VM optimizada para memoria
    public VirtualMachine constructMemoryOptimizedVM(VMBuilder builder, String provider) {
        int[] specs = getSpecs(provider, "memory");
        System.out.println("Construyendo VM optimizada en memoria para " + provider + " con " + specs[0] + " vCPU y " + specs[1] + " GB RAM");
        return buildVM(builder, specs[0], specs[1], "memory");
    }

    //  Construcción de VM optimizada para cómputo
    public VirtualMachine constructComputeOptimizedVM(VMBuilder builder, String provider) {
        int[] specs = getSpecs(provider, "compute");
        System.out.println("Construyendo VM optimizada en cómputo para " + provider + " con " + specs[0] + " vCPU y " + specs[1] + " GB RAM");
        return buildVM(builder, specs[0], specs[1], "compute");
    }

    //  Método que ejecuta los pasos del builder según el tipo de máquina
    private VirtualMachine buildVM(VMBuilder builder, int vcpus, int memoryGB, String type) {
        builder.setBasicConfiguration();
        if (type.equals("memory")) builder.enableMemoryOptimization();
        if (type.equals("compute")) builder.enableDiskOptimization();
        builder.setSecurityOptions();
        VirtualMachine vm = builder.build();

        System.out.println("✔️ Máquina virtual creada con " + vcpus + " vCPU y " + memoryGB + " GB RAM");
        return vm;
    }

    //  Especificaciones predefinidas por proveedor y tipo
    private int[] getSpecs(String provider, String type) {
        // Retorna [vCPU, MemoriaGB]
        return switch (provider.toLowerCase()) {
            case "aws" -> switch (type) {
                case "memory" -> new int[]{4, 32};
                case "compute" -> new int[]{4, 8};
                default -> new int[]{2, 8};
            };
            case "azure" -> switch (type) {
                case "memory" -> new int[]{4, 32};
                case "compute" -> new int[]{4, 8};
                default -> new int[]{2, 8};
            };
            case "gcp" -> switch (type) {
                case "memory" -> new int[]{4, 32};
                case "compute" -> new int[]{4, 4};
                default -> new int[]{2, 8};
            };
            case "onprem" -> switch (type) {
                case "memory" -> new int[]{4, 32};
                case "compute" -> new int[]{4, 4};
                default -> new int[]{2, 8};
            };
            default -> new int[]{2, 4};
        };
    }
}
