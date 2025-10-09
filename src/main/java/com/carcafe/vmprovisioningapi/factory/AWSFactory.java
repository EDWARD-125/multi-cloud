package com.carcafe.vmprovisioningapi.factory;


import com.carcafe.vmprovisioningapi.model.network.AWSNetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.AWSStorageProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.AWSProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;

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
