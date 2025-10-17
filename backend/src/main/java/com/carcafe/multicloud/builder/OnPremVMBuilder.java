package com.carcafe.multicloud.builder;

import java.util.Arrays;

public class OnPremVMBuilder implements VMBuilder {

    private VirtualMachine.Builder builder;

    public OnPremVMBuilder(int vcpus, int memoryGB) {
        builder = new VirtualMachine.Builder("On-Premise", vcpus, memoryGB);
    }

    @Override
    public void setBasicConfiguration() {
        builder.setRegion("datacenter-local")
               .setKeyPairName("local-key")
               .setPublicIP(false); // No IP pública
    }

    @Override
    public void enableMemoryOptimization() {
        builder.setMemoryOptimization(true);
        builder.setIops(1000);
    }

    @Override
    public void enableDiskOptimization() {
        builder.setDiskOptimization(true);
        builder.setIops(1500);
    }

    @Override
    public void setSecurityOptions() {
        builder.setFirewallRules(Arrays.asList("SSH", "Internal Access"));
    }

    @Override
    public VirtualMachine build() {
        return builder.build();
    }
}
