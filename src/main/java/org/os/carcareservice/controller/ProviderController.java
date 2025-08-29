package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.*;
import org.os.carcareservice.service.ProviderService;
import org.os.carcareservice.service.RequestService;
import org.os.carcareservice.service.RequestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    private final RequestService requestService;

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

    @GetMapping("/{id}/requests")
    public ResponseEntity<List<RequestResponse>> getProviderRequests (@PathVariable Long id) {
        List<RequestResponse>  response = requestService.getProviderRequests(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
//    @GetMapping("/{token}/requests")
//    public ResponseEntity<List<RequestResponse>> getProviderRequestsByToken (@PathVariable Long id) {
//        List<RequestResponse>  response = requestService.getProviderRequests(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/{id}/requests/history")
    public ResponseEntity<List<StatusHistoryResponse>> getProviderRequestsHistory(
            @PathVariable("id") Long providerId) {

        List<StatusHistoryResponse> responses = requestService.getProviderRequestsHistory(providerId);
        return ResponseEntity.ok(responses);
    }
}
