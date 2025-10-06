package com.carcafe.vmprovisioningapi.factory;

import com.carcafe.vmprovisioningapi.model.VMProvisioner;
import com.carcafe.vmprovisioningapi.model.NetworkProvisioner;
import com.carcafe.vmprovisioningapi.model.StorageProvisioner;

public interface AbstractFactory {
    VMProvisioner createVMProvisioner();
    NetworkProvisioner createNetworkProvisioner();
    StorageProvisioner createStorageProvisioner();
}
