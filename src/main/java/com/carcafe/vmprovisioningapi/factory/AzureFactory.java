package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;
import org.springframework.stereotype.Component;

@Component
public class AzureFactory implements VMFactory {
    @Override
    public VMProvisioner createVMProvisioner() { return new AzureProvisioner(); }
    @Override
    public String providerName() { return "azure"; }
}
