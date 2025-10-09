package com.carcafe.vmprovisioningapi.controller;

public class ProvisionRequest {
    private String proveedor;
    private String nombreVM;
    private int cpu;
    private int memoria;
    private int almacenamiento;

    // Getters y setters
    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public String getNombreVM() { return nombreVM; }
    public void setNombreVM(String nombreVM) { this.nombreVM = nombreVM; }

    public int getCpu() { return cpu; }
    public void setCpu(int cpu) { this.cpu = cpu; }

    public int getMemoria() { return memoria; }
    public void setMemoria(int memoria) { this.memoria = memoria; }

    public int getAlmacenamiento() { return almacenamiento; }
    public void setAlmacenamiento(int almacenamiento) { this.almacenamiento = almacenamiento; }
}
