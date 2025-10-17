package com.carcafe.multicloud.factory;


import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.network.OnPremNetworkProvisioner;
import com.carcafe.multicloud.model.storage.OnPremStorageProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;
import com.carcafe.multicloud.model.vm.OnPremProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;

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
