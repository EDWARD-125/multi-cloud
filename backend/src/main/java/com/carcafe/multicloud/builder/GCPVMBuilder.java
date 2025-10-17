package com.carcafe.multicloud.builder;

import java.util.Arrays;

public class GCPVMBuilder implements VMBuilder {

    private VirtualMachine.Builder builder;

    public GCPVMBuilder(int vcpus, int memoryGB) {
        builder = new VirtualMachine.Builder("GCP", vcpus, memoryGB);
    }

    @Override
    public void setBasicConfiguration() {
        builder.setRegion("us-central1")
               .setKeyPairName("gcp-key")
               .setPublicIP(true);
    }

    @Override
    public void enableMemoryOptimization() {
        builder.setMemoryOptimization(true);
        builder.setIops(2000);
    }

    @Override
    public void enableDiskOptimization() {
        builder.setDiskOptimization(true);
        builder.setIops(3500);
    }

    @Override
    public void setSecurityOptions() {
        builder.setFirewallRules(Arrays.asList("SSH", "HTTPS"));
    }

    @Override
    public VirtualMachine build() {
        return builder.build();
    }
}
