package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.VMProvisioner;

public abstract class VMFactory {
    public abstract VMProvisioner createProvisioner();
}
