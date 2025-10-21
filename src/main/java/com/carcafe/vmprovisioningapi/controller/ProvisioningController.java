package com.carcafe.vmprovisioningapi.controller;

import com.carcafe.vmprovisioningapi.dto.ProvisioningRequest;
import com.carcafe.vmprovisioningapi.dto.ProvisioningResponse;
import com.carcafe.vmprovisioningapi.service.ProvisioningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/provisioning")
@RequiredArgsConstructor
public class ProvisioningController {

    private final ProvisioningService service;

    @PostMapping("/vm")
    public ResponseEntity<ProvisioningResponse> provisionVM(@Valid @RequestBody ProvisioningRequest req) {
        ProvisioningResponse resp = service.provision(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().body(java.util.Map.of("status","UP"));
    }
}
