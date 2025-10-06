package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.*;

public class GCPFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new GCPProvisioner(); // Ya la tienes
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new GCPNetworkProvisioner(); // Nueva clase creada
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new GCPStorageProvisioner(); // Nueva clase creada
    }
}
