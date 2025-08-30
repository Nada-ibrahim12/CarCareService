package org.os.carcareservice.service;

import org.os.carcareservice.dto.AvailabilityRequest;
import org.os.carcareservice.dto.AvailabilityResponse;
import java.util.List;

public interface AvailabilityService {
    List<AvailabilityResponse> getProviderAvailability(Long providerId);
    List<AvailabilityResponse> updateProviderAvailability(String email, List<AvailabilityRequest> availabilityRequests);
    void deleteProviderAvailability(Long id);
    void deleteProviderAvailability(String email);
}