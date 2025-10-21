package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;

public class AWSFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new AWSProvisioner(); // Ya la tienes
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new AWSNetworkProvisioner(); // Nueva clase creada
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new AWSStorageProvisioner(); // Nueva clase creada
    }
}
