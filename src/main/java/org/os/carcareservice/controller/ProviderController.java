package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.ProviderRegistrationRequest;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.dto.ProviderUpdateRequest;
import org.os.carcareservice.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @PostMapping("/register")
    public ResponseEntity<ProviderResponse> registerProvider (
            @Valid
            @RequestBody
            ProviderRegistrationRequest providerRegistrationRequest) {
        ProviderResponse response = providerService.registerProvider(providerRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ProviderResponse> getProviderByEmail (@PathVariable("email") String email) {
        ProviderResponse response = providerService.getProviderByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProviderById (@PathVariable("id") Long id) {
        ProviderResponse response = providerService.getProviderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // for provider only (will be secured)
    @GetMapping("/{id}/private")
    public ResponseEntity<ProviderResponse> getProviderPrivate(@PathVariable Long id) {
        ProviderResponse response = providerService.getProviderById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderResponse> updateProvider (
            @PathVariable Long id,
            @Valid
            @RequestBody
            ProviderUpdateRequest request
    ) {
        ProviderResponse response = providerService.updateProvider(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = providerService.getAllProviders();
        return ResponseEntity.status(HttpStatus.OK).body(providers);
    }
}
