package com.carcafe.multicloud.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * DTO que representa la solicitud del usuario para aprovisionar una VM.
 * Recoge todos los parámetros posibles definidos por el patrón Builder.
 */
public class ProvisionRequest {

    // --- CAMPOS OBLIGATORIOS ---
    @NotBlank(message = "El campo 'proveedor' es obligatorio.")
    private String proveedor;

    @NotBlank(message = "El campo 'nombreVM' es obligatorio.")
    private String nombreVM;

    @Min(value = 1, message = "El número de CPU debe ser mayor que 0.")
    private int cpu;

    @Min(value = 1, message = "La memoria debe ser mayor que 0 GB.")
    private int memoria;

    @Min(value = 1, message = "El almacenamiento debe ser mayor que 0 GB.")
    private int almacenamiento;

    // --- CAMPOS OPCIONALES (BUILDER) ---
    private String region;
    private String keyPairName;
    private List<String> firewallRules;
    private boolean publicIP;
    private int iops;
    private boolean memoryOptimization;
    private boolean diskOptimization;

    // --- Getters y Setters ---
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombreVM() {
        return nombreVM;
    }

    public void setNombreVM(String nombreVM) {
        this.nombreVM = nombreVM;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getKeyPairName() {
        return keyPairName;
    }

    public void setKeyPairName(String keyPairName) {
        this.keyPairName = keyPairName;
    }

    public List<String> getFirewallRules() {
        return firewallRules;
    }

    public void setFirewallRules(List<String> firewallRules) {
        this.firewallRules = firewallRules;
    }

    public boolean isPublicIP() {
        return publicIP;
    }

    public void setPublicIP(boolean publicIP) {
        this.publicIP = publicIP;
    }

    public int getIops() {
        return iops;
    }

    public void setIops(int iops) {
        this.iops = iops;
    }

    public boolean isMemoryOptimization() {
        return memoryOptimization;
    }

    public void setMemoryOptimization(boolean memoryOptimization) {
        this.memoryOptimization = memoryOptimization;
    }

    public boolean isDiskOptimization() {
        return diskOptimization;
    }

    public void setDiskOptimization(boolean diskOptimization) {
        this.diskOptimization = diskOptimization;
    }
}
