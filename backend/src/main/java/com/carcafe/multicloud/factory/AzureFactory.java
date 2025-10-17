package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.*;
import com.carcafe.multicloud.model.network.*;
import com.carcafe.multicloud.model.storage.*;

/**
 * Fábrica concreta para el proveedor Microsoft Azure.
 */
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
