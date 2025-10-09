package com.carcafe.vmprovisioningapi.builder;

import java.util.List;

public class VirtualMachine {

    // --- Atributos obligatorios ---
    private String provider;
    private int vcpus;
    private int memoryGB;

    // --- Atributos opcionales ---
    private boolean memoryOptimization;
    private boolean diskOptimization;
    private String keyPairName;
    private String region;
    private List<String> firewallRules;
    private boolean publicIP;
    private int iops;

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

    @Override
    public String toString() {
        return "\n Virtual Machine {" +
                "\n  provider='" + provider + '\'' +
                ", vcpus=" + vcpus +
                ", memoryGB=" + memoryGB +
                ", memoryOptimization=" + memoryOptimization +
                ", diskOptimization=" + diskOptimization +
                ", keyPairName='" + keyPairName + '\'' +
                ", region='" + region + '\'' +
                ", firewallRules=" + firewallRules +
                ", publicIP=" + publicIP +
                ", iops=" + iops +
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
