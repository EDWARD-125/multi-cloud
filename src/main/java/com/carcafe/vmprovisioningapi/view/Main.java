package com.carcafe.vmprovisioningapi.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.carcafe.vmprovisioningapi")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 VM Provisioning API iniciada exitosamente!");
        System.out.println("=".repeat(60));
        System.out.println("📄 Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("🔍 Health: http://localhost:8080/api/v1/provisioning/health");
        System.out.println("=".repeat(60) + "\n");
    }
}