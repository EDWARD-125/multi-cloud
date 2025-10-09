package com.carcafe.vmprovisioningapi.builder;

import java.util.Arrays;

public class AWSVMBuilder implements VMBuilder {

    private VirtualMachine.Builder builder;

    public AWSVMBuilder(int vcpus, int memoryGB) {
        builder = new VirtualMachine.Builder("AWS", vcpus, memoryGB);
    }

    @Override
    public void setBasicConfiguration() {
        builder.setRegion("us-east-1")
               .setKeyPairName("aws-default-key")
               .setPublicIP(true);
    }

    @Override
    public void enableMemoryOptimization() {
        builder.setMemoryOptimization(true);
    }

    @Override
    public void enableDiskOptimization() {
        builder.setDiskOptimization(true);
        builder.setIops(3000);
    }

    @Override
    public void setSecurityOptions() {
        builder.setFirewallRules(Arrays.asList("SSH", "HTTP"));
    }

    @Override
    public VirtualMachine build() {
        return builder.build();
    }
}
