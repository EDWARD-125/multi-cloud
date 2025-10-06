package com.carcafe.vmprovisioningapi.model;

public interface VMProvisioner {
    void provisionVM(String nombreVM, int cpu, int memoria, int almacenamiento);
}
