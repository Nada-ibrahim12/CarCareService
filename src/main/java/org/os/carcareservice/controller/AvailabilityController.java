package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.AvailabilityRequest;
import org.os.carcareservice.dto.AvailabilityResponse;
import org.os.carcareservice.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{providerId}/availability")
    public ResponseEntity<List<AvailabilityResponse>> updateAvailability(
            @PathVariable Long providerId,
            @Valid @RequestBody List<AvailabilityRequest> availabilityRequests) {

        List<AvailabilityResponse> updatedAvailability =
                availabilityService.updateProviderAvailability(providerId, availabilityRequests);

        return ResponseEntity.ok(updatedAvailability);
    }

    @DeleteMapping("/{providerId}/availability")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long providerId) {
        availabilityService.deleteProviderAvailability(providerId);
        return ResponseEntity.noContent().build();
    }
}