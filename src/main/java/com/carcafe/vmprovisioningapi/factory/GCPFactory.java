package com.carcafe.vmprovisioningapi.factory;


import com.carcafe.vmprovisioningapi.model.network.GCPNetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.GCPStorageProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.GCPProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;

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
