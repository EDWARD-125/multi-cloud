package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.OnPremProvisioner;
import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public class OnPremFactory extends VMFactory {
    @Override
    public VMProvisioner createProvisioner() {
        return new OnPremProvisioner();
    }
}
