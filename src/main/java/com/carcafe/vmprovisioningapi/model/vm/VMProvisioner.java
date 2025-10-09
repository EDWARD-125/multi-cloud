package com.carcafe.vmprovisioningapi.model.vm;

public interface VMProvisioner {
    void provisionVM(String nombreVM, int cpu, int memoria, int almacenamiento);
}
