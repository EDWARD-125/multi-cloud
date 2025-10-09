package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.network.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.storage.StorageProvisioner;
import com.carcafe.vmprovisioningapi.model.vm.VMProvisioner;

public interface AbstractFactory {
    VMProvisioner createVMProvisioner();
    NetworkProvisioner createNetworkProvisioner();
    StorageProvisioner createStorageProvisioner();
}
