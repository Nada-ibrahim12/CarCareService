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
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    // ==================================================================================================
    /**
     * 🔹 Get provider availability (public)
     *
     * Method: GET
     * Endpoint: /api/providers/{providerId}/availability
     * Auth: ❌ No authentication required
     *
     * @param providerId Provider ID (path variable)
     * @return List of availability slots
     *
     * Example:
     *   GET http://localhost:8080/api/providers/3/availability
     *
     * Response: 200 OK
     * [
     *   {
     *     "id": 1,
     *     "dayOfWeek": "MONDAY",
     *     "startTime": "09:00:00",
     *     "endTime": "17:00:00",
     *     "isAvailable": true
     *   },
     *   {
     *     "id": 2,
     *     "dayOfWeek": "TUESDAY",
     *     "startTime": "10:00:00",
     *     "endTime": "15:00:00",
     *     "isAvailable": false
     *   }
     * ]
     */
    @GetMapping("/{providerId}/availability")
    public ResponseEntity<List<AvailabilityResponse>> getAvailabilityByProviderId(
            @PathVariable Long providerId) {
        List<AvailabilityResponse> availability = availabilityService.getProviderAvailability(providerId);
        return ResponseEntity.ok(availability);
    }

    // ==================================================================================================
    /**
     * 🔹 Update provider availability
     *
     * Method: PUT
     * Endpoint: /api/providers/availability
     * Auth: 🔐 Provider role required (Bearer token)
     *
     * @param authentication Authenticated provider (Spring Security)
     * @param availabilityRequests JSON list of availability slots
     *
     * Example Request:
     * PUT http://localhost:8080/api/providers/availability
     * Headers:
     *   Authorization: Bearer <token>
     * Body (JSON):
     * [
     *   {
     *     "dayOfWeek": "MONDAY",
     *     "startTime": "09:00:00",
     *     "endTime": "17:00:00",
     *     "isAvailable": true
     *   },
     *   {
     *     "dayOfWeek": "TUESDAY",
     *     "startTime": "10:00:00",
     *     "endTime": "14:00:00",
     *     "isAvailable": true
     *   }
     * ]
     *
     * Response: 200 OK
     * [
     *   {
     *     "id": 5,
     *     "dayOfWeek": "MONDAY",
     *     "startTime": "09:00:00",
     *     "endTime": "17:00:00",
     *     "isAvailable": true
     *   },
     *   {
     *     "id": 6,
     *     "dayOfWeek": "TUESDAY",
     *     "startTime": "10:00:00",
     *     "endTime": "14:00:00",
     *     "isAvailable": true
     *   }
     * ]
     */
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

    // ==================================================================================================
    /**
     * 🔹 Delete provider availability
     *
     * Method: DELETE
     * Endpoint: /api/providers/availability
     * Auth: 🔐 Provider role required (Bearer token)
     *
     * @param authentication Authenticated provider
     *
     * Example:
     *   DELETE http://localhost:8080/api/providers/availability
     *   Authorization: Bearer <token>
     *
     * Response: 204 No Content
     */
    @PreAuthorize("hasRole('PROVIDER')")
    @DeleteMapping("/availability")
    public ResponseEntity<Void> deleteAvailability(Authentication authentication) {
        String email = authentication.getName();
        User currentUser = (User) authentication.getPrincipal();
        availabilityService.deleteProviderAvailability(email);
        return ResponseEntity.noContent().build();
    }
}
