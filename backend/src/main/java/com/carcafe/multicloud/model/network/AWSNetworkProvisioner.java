package com.carcafe.multicloud.model.network;

public class AWSNetworkProvisioner implements NetworkProvisioner {
    @Override
    public void configurarRed(String nombreRed) {
        System.out.println(" [AWS] Configurando red para: " + nombreRed);
        System.out.println("vpcId: vpc-" + (int)(Math.random()*1000));
        System.out.println("subnetId: subnet-" + (int)(Math.random()*1000));
        System.out.println("securityGroup: sg-" + (int)(Math.random()*1000));
    }
}
