package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;

public class AzureFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new AzureProvisioner(); // Ya la tienes
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new AzureNetworkProvisioner(); // Nueva clase creada
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new AzureStorageProvisioner(); // Nueva clase creada
    }
}
