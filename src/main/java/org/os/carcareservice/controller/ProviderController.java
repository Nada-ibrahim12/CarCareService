package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.*;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.ProviderService;
import org.os.carcareservice.service.RequestService;
import org.os.carcareservice.service.RequestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor

public class ProviderController {
    private final ProviderService providerService;
    private final RequestService requestService;

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

    @PutMapping("/profile")
    public ResponseEntity<ProviderResponse> updateProvider (
            Authentication authentication,
            @Valid
            @RequestBody
            ProviderUpdateRequest request
    ) {
        User currentUser = (User) authentication.getPrincipal();
        Long id = currentUser.getId();
        ProviderResponse response = providerService.updateProvider(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = providerService.getAllProviders();
        return ResponseEntity.status(HttpStatus.OK).body(providers);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("/requests")
    public ResponseEntity<List<RequestResponse>> getProviderRequests (Authentication authentication) {
        String email = authentication.getName();
        List<RequestResponse>  response = requestService.getProviderRequests(email);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
//    @GetMapping("/{token}/requests")
//    public ResponseEntity<List<RequestResponse>> getProviderRequestsByToken (@PathVariable Long id) {
//        List<RequestResponse>  response = requestService.getProviderRequests(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("/requests/history")
    public ResponseEntity<List<StatusHistoryResponse>> getProviderRequestsHistory(
            Authentication authentication) {
        String email = authentication.getName();
        List<StatusHistoryResponse> responses = requestService.getProviderRequestsHistory(email);
        return ResponseEntity.ok(responses);
    }
}
