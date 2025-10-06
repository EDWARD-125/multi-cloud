package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.AWSProvisioner;
import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public class AWSFactory extends VMFactory {
    @Override
    public VMProvisioner createProvisioner() {
        return new AWSProvisioner();
    }
}
