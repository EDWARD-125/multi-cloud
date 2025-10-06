package com.carcafe.vmprovisioningapi.view;

import com.carcafe.vmprovisioningapi.controller.ProvisioningAPI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Simulación de Aprovisionamiento Multi-Cloud ===");
        System.out.println("Seleccione el proveedor de nube:");
        System.out.println("1. AWS");
        System.out.println("2. Microsoft Azure");
        System.out.println("3. Google Cloud Platform");
        System.out.println("4. On-Premise");
        System.out.println("------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();

        ProvisioningAPI api = new ProvisioningAPI();

        switch (opcion) {
            case 1 -> api.provisionar("aws", "ServidorAWS", 4, 16, 100);
            case 2 -> api.provisionar("azure", "ServidorAzure", 2, 8, 200);
            case 3 -> api.provisionar("gcp", "ServidorGCP", 8, 32, 500);
            case 4 -> api.provisionar("onprem", "ServidorLocal", 16, 64, 1000);
            default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
        }

        scanner.close();
    }
}


