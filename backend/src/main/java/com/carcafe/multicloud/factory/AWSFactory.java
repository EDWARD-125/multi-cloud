package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.AWSProvisioner;
import com.carcafe.multicloud.model.vm.VMProvisioner;
import com.carcafe.multicloud.model.network.AWSNetworkProvisioner;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.AWSStorageProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;

/**
 * Fábrica concreta que crea los recursos específicos de AWS.
 */
public class AWSFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new AWSProvisioner();
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new AWSNetworkProvisioner();
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new AWSStorageProvisioner();
    }
}
