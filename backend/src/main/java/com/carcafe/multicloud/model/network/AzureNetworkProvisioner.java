package com.carcafe.multicloud.model.network;

public class AzureNetworkProvisioner implements NetworkProvisioner {
    @Override
    public void configurarRed(String nombreRed) {
        System.out.println(" [AZURE] Configurando red virtual para: " + nombreRed);
        System.out.println("virtualNetwork: vnet-" + (int)(Math.random()*1000));
        System.out.println("subnetName: subnet-" + (int)(Math.random()*1000));
        System.out.println("networkSecurityGroup: nsg-" + (int)(Math.random()*1000));
    }
}
