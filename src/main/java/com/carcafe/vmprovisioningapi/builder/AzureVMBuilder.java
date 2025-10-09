package com.carcafe.vmprovisioningapi.builder;

import java.util.Arrays;

public class AzureVMBuilder implements VMBuilder {

    private VirtualMachine.Builder builder;

    public AzureVMBuilder(int vcpus, int memoryGB) {
        builder = new VirtualMachine.Builder("Azure", vcpus, memoryGB);
    }

    @Override
    public void setBasicConfiguration() {
        builder.setRegion("eastus")
               .setKeyPairName("azure-key")
               .setPublicIP(true);
    }

    @Override
    public void enableMemoryOptimization() {
        builder.setMemoryOptimization(true);
        builder.setIops(2500);
    }

    @Override
    public void enableDiskOptimization() {
        builder.setDiskOptimization(true);
        builder.setIops(4000);
    }

    @Override
    public void setSecurityOptions() {
        builder.setFirewallRules(Arrays.asList("RDP", "HTTPS"));
    }

    @Override
    public VirtualMachine build() {
        return builder.build();
    }
}
