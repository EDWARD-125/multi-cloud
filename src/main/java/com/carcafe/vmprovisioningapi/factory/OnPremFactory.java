package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;

public class OnPremFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new OnPremProvisioner(); // Ya la tienes
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new OnPremNetworkProvisioner(); // Nueva clase creada
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new OnPremStorageProvisioner(); // Nueva clase creada
    }
}
