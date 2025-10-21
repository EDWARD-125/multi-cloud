package com.carcafe.vmprovisioningapi.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.carcafe.vmprovisioningapi")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
