package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;
import org.springframework.stereotype.Component;

@Component
public class GCPFactory implements VMFactory {
    @Override
    public VMProvisioner createVMProvisioner() { return new GCPProvisioner(); }
    @Override
    public String providerName() { return "gcp"; }
}
