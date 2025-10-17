package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.*;
import com.carcafe.multicloud.model.network.*;
import com.carcafe.multicloud.model.storage.*;

/**
 * Fábrica concreta para Google Cloud Platform.
 */
public class GCPFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new GCPProvisioner();
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new GCPNetworkProvisioner();
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new GCPStorageProvisioner();
    }
}
