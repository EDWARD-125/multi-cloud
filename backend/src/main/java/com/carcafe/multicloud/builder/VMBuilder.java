package com.carcafe.multicloud.builder;

public interface VMBuilder {
    void setBasicConfiguration();
    void enableMemoryOptimization();
    void enableDiskOptimization();
    void setSecurityOptions();
    VirtualMachine build();
}
