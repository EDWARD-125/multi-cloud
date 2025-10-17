package com.carcafe.multicloud.factory;

import com.carcafe.multicloud.model.vm.VMProvisioner;
import com.carcafe.multicloud.model.network.NetworkProvisioner;
import com.carcafe.multicloud.model.storage.StorageProvisioner;

/**
 * Interfaz para crear familias de objetos relacionados (VM, Network, Storage)
 * según el proveedor de nube.
 */
public interface AbstractFactory {
    VMProvisioner createVMProvisioner();
    NetworkProvisioner createNetworkProvisioner();
    StorageProvisioner createStorageProvisioner();
}
