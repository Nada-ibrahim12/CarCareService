package org.os.carcareservice.controller;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.UserStatus;
import org.os.carcareservice.service.AdminProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/providers")
@RequiredArgsConstructor
public class AdminProviderController {

    private final AdminProviderService adminProviderService;

    @PutMapping("/{id}/verify")
    public ResponseEntity<ProviderResponse> verifyProvider(
            @PathVariable Long id,
            @RequestParam Boolean isVerified) {

        ProviderResponse response = adminProviderService.verifyProvider(id, isVerified);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProviderResponse> updateProviderStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {

        ProviderResponse response = adminProviderService.updateProviderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = adminProviderService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/users-status/{status}")
    public ResponseEntity<List<ProviderResponse>> getProvidersByUserStatus(
            @PathVariable UserStatus status) {

        List<ProviderResponse> providers = adminProviderService.getProvidersByUserStatus(status);
        return ResponseEntity.ok(providers);
    }
}