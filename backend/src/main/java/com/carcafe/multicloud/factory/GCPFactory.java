package com.carcafe.multicloud.factory;


import com.carcafe.multicloud.model.network.GCPNetworkProvisioner;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.GCPStorageProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;
import com.carcafe.multicloud.model.vm.GCPProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;

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
