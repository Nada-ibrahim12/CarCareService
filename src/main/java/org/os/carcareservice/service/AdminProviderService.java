package org.os.carcareservice.service;

import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.UserStatus;

import java.util.List;

public interface AdminProviderService {
    ProviderResponse verifyProvider(Long id, boolean isVerified);

    ProviderResponse updateProviderStatus(Long id, UserStatus status);

    List<ProviderResponse> getAllProviders();
    List<ProviderResponse> getProvidersByUserStatus(UserStatus status);
}
