package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;
import org.springframework.stereotype.Component;

@Component
public class OnPremFactory implements VMFactory {
    @Override
    public VMProvisioner createVMProvisioner() { return new OnPremProvisioner(); }
    @Override
    public String providerName() { return "onprem"; }
}
