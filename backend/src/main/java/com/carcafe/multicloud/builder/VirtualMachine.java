package com.carcafe.multicloud.builder;

import com.carcafe.multicloud.prototype.VMPrototype;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una Máquina Virtual dentro del sistema Multi-Cloud.
 * Integra los patrones:
 * - Builder: para construcción paso a paso.
 * - Abstract Factory: para agregar componentes según el proveedor.
 * - Prototype: para clonar VMs existentes con facilidad.
 */
public class VirtualMachine implements VMPrototype {

    // --- Atributos obligatorios ---
    private String provider;
    private int vcpus;
    private int memoryGB;

    // --- Atributos opcionales (Builder) ---
    private boolean memoryOptimization;
    private boolean diskOptimization;
    private String keyPairName;
    private String region;
    private List<String> firewallRules;
    private boolean publicIP;
    private int iops;

    // --- Atributos de Abstract Factory ---
    private String network;       // nombre o id de red
    private String diskType;      // HDD, SSD, etc.
    private String os;            // sistema operativo base

    // --- Estado general ---
    private String estado;        // "Provisionada correctamente", "Error", etc.

    // Constructor privado (solo lo usa el builder)
    private VirtualMachine() {}

    // --- Getters ---
    public String getProvider() { return provider; }
    public int getVcpus() { return vcpus; }
    public int getMemoryGB() { return memoryGB; }
    public boolean isMemoryOptimization() { return memoryOptimization; }
    public boolean isDiskOptimization() { return diskOptimization; }
    public String getKeyPairName() { return keyPairName; }
    public String getRegion() { return region; }
    public List<String> getFirewallRules() { return firewallRules; }
    public boolean isPublicIP() { return publicIP; }
    public int getIops() { return iops; }
    public String getNetwork() { return network; }
    public String getDiskType() { return diskType; }
    public String getOs() { return os; }
    public String getEstado() { return estado; }

    // --- Setters (solo para fábrica o director) ---
    public void setNetwork(String network) { this.network = network; }
    public void setDiskType(String diskType) { this.diskType = diskType; }
    public void setOs(String os) { this.os = os; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- Setters adicionales para compatibilidad con el controlador ---
    public void setRegion(String region) { this.region = region; }
    public void setIops(int iops) { this.iops = iops; }

    // --- Método del patrón Prototype ---
    @Override
    public VMPrototype clone() {
        try {
            VirtualMachine clone = (VirtualMachine) super.clone();
            // Clonación profunda de la lista de reglas de firewall
            clone.firewallRules = this.firewallRules != null
                    ? new ArrayList<>(this.firewallRules)
                    : new ArrayList<>();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar la máquina virtual", e);
        }
    }

    @Override
    public String toString() {
        return "\n Virtual Machine {" +
                "\n  provider='" + provider + '\'' +
                ", vcpus=" + vcpus +
                ", memoryGB=" + memoryGB +
                ", region='" + region + '\'' +
                ", network='" + network + '\'' +
                ", diskType='" + diskType + '\'' +
                ", os='" + os + '\'' +
                ", iops=" + iops +
                ", memoryOptimization=" + memoryOptimization +
                ", diskOptimization=" + diskOptimization +
                ", keyPairName='" + keyPairName + '\'' +
                ", firewallRules=" + firewallRules +
                ", publicIP=" + publicIP +
                ", estado='" + estado + '\'' +
                "\n}";
    }

    // --- Builder interno estático ---
    public static class Builder {
        private final VirtualMachine vm;

        public Builder(String provider, int vcpus, int memoryGB) {
            vm = new VirtualMachine();
            vm.provider = provider;
            vm.vcpus = vcpus;
            vm.memoryGB = memoryGB;
        }

        public Builder setMemoryOptimization(boolean memoryOptimization) {
            vm.memoryOptimization = memoryOptimization;
            return this;
        }

        public Builder setDiskOptimization(boolean diskOptimization) {
            vm.diskOptimization = diskOptimization;
            return this;
        }

        public Builder setKeyPairName(String keyPairName) {
            vm.keyPairName = keyPairName;
            return this;
        }

        public Builder setRegion(String region) {
            vm.region = region;
            return this;
        }

        public Builder setFirewallRules(List<String> rules) {
            vm.firewallRules = rules;
            return this;
        }

        public Builder setPublicIP(boolean publicIP) {
            vm.publicIP = publicIP;
            return this;
        }

        public Builder setIops(int iops) {
            vm.iops = iops;
            return this;
        }

        public VirtualMachine build() {
            return vm;
        }
    }
}
