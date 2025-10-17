package com.carcafe.multicloud.factory;


import com.carcafe.multicloud.model.network.AWSNetworkProvisioner;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.AWSStorageProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;
import com.carcafe.multicloud.model.vm.AWSProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;

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
