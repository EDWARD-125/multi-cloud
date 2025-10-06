package com.carcafe.vmprovisioningapi.view;

import com.carcafe.vmprovisioningapi.controller.ProvisioningAPI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Simulación de Aprovisionamiento Multi-Cloud ===");
        System.out.println("1. AWS");
        System.out.println("2. Azure");
        System.out.println("3. GCP");
        System.out.println("4. On-Premise");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        ProvisioningAPI api = new ProvisioningAPI();

        switch (opcion) {
            case 1 -> api.aprovisionar("aws", "ServidorAWS", 4, 16, 100);
            case 2 -> api.aprovisionar("azure", "ServidorAzure", 2, 8, 200);
            case 3 -> api.aprovisionar("gcp", "ServidorGCP", 8, 32, 500);
            case 4 -> api.aprovisionar("onprem", "ServidorLocal", 16, 64, 1000);
            default -> System.out.println("❌ Opción no válida.");
        }

        scanner.close();
    }
}
