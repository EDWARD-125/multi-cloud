package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.GCPProvisioner;
import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public class GCPFactory extends VMFactory {
    @Override
    public VMProvisioner createProvisioner() {
        return new GCPProvisioner();
    }
}
