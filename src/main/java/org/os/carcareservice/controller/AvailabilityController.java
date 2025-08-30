package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.AvailabilityRequest;
import org.os.carcareservice.dto.AvailabilityResponse;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping("/{providerId}/availability")
    public ResponseEntity<List<AvailabilityResponse>> getAvailabilityByProviderId(
            @PathVariable Long providerId) {
        List<AvailabilityResponse> availability = availabilityService.getProviderAvailability(providerId);
        return ResponseEntity.ok(availability);
    }

//    @GetMapping("/{token}/availability")
//    public ResponseEntity<List<AvailabilityResponse>> getAvailabilityByProviderToken(
//            @PathVariable String token) {
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
//    }

    // for now using provider ID, later will use token
    @PreAuthorize("hasRole('PROVIDER')")
    @PutMapping("/availability")
    public ResponseEntity<List<AvailabilityResponse>> updateAvailability(
            Authentication authentication,
            @Valid @RequestBody List<AvailabilityRequest> availabilityRequests) {
        String email = authentication.getName();
        List<AvailabilityResponse> updatedAvailability =
                availabilityService.updateProviderAvailability(email, availabilityRequests);

        return ResponseEntity.ok(updatedAvailability);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @DeleteMapping("/availability")
    public ResponseEntity<Void> deleteAvailability(Authentication authentication) {
        String email = authentication.getName();
        User currentUser = (User) authentication.getPrincipal();
        availabilityService.deleteProviderAvailability(email);
        return ResponseEntity.noContent().build();
    }
}