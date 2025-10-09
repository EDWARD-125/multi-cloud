package com.carcafe.vmprovisioningapi.factory;


import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.network.OnPremNetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.OnPremStorageProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.OnPremProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;

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
