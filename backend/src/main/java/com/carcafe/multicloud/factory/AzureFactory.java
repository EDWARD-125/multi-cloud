package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.AzureProvisioner;
import com.carcafe.multicloud.model.network.AzureNetworkProvisioner;
import com.carcafe.multicloud.model.storage.AzureStorageProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;

public class AzureFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new AzureProvisioner();
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new AzureNetworkProvisioner();
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new AzureStorageProvisioner();
    }
}
