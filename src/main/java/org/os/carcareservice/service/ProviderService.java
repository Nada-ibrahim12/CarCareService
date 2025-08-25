package org.os.carcareservice.service;

import jakarta.validation.Valid;
import org.os.carcareservice.dto.ProviderRegistrationRequest;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.dto.ProviderUpdateRequest;

import java.util.List;

public interface ProviderService {
    ProviderResponse registerProvider(ProviderRegistrationRequest request);
    ProviderResponse getProviderById(Long id);
    ProviderResponse getProviderByEmail(String email);
    ProviderResponse updateProvider(Long ProviderId, @Valid ProviderUpdateRequest request);

    List<ProviderResponse> getAllProviders();
}
