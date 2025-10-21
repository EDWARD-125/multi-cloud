package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;
import org.springframework.stereotype.Component;

@Component
public class AWSFactory implements VMFactory {
    @Override
    public VMProvisioner createVMProvisioner() {
        return new AWSProvisioner();
    }

    @Override
    public String providerName() { return "aws"; }
}
