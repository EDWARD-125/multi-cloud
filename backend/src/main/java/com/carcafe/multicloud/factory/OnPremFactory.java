package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.*;
import com.carcafe.multicloud.model.network.*;
import com.carcafe.multicloud.model.storage.*;

/**
 * Fábrica concreta para entornos On-Premise (infraestructura local).
 */
public class OnPremFactory implements AbstractFactory {

    @Override
    public VMProvisioner createVMProvisioner() {
        return new OnPremProvisioner();
    }

    @Override
    public NetworkProvisioner createNetworkProvisioner() {
        return new OnPremNetworkProvisioner();
    }

    @Override
    public StorageProvisioner createStorageProvisioner() {
        return new OnPremStorageProvisioner();
    }
}
