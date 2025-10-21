package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public interface VMFactory {
    VMProvisioner createVMProvisioner();
    String providerName();
}
