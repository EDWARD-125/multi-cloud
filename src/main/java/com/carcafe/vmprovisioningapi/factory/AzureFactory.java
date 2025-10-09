package com.carcafe.vmprovisioningapi.factory;


import com.carcafe.vmprovisioningapi.model.network.AzureNetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.AzureStorageProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.AzureProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;

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
