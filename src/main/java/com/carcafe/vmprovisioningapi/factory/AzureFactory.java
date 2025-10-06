package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.AzureProvisioner;
import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public class AzureFactory extends VMFactory {
    @Override
    public VMProvisioner createProvisioner() {
        return new AzureProvisioner();
    }
}
