package org.os.carcareservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.entity.UserStatus;
import org.os.carcareservice.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProviderServiceImpl implements AdminProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderService providerService;

    @Override
    public ProviderResponse verifyProvider(Long id, boolean isVerified) {
        Provider provider = providerRepository.findById(id).get();
        provider.setIsVerified(isVerified);
        Provider updatedProvider = providerRepository.save(provider);
        return providerService.mapToProviderResponse(updatedProvider);
    }

    @Override
    @Transactional
    public ProviderResponse updateProviderStatus(Long id, UserStatus status) {
        Provider provider = providerRepository.findById(id).get();
        provider.setStatus(status);
        Provider updatedProvider = providerRepository.save(provider);
        return providerService.mapToProviderResponse(updatedProvider);
    }

    @Override
    public List<ProviderResponse> getAllProviders() {
        return providerService.getAllProviders();
    }

    @Override
    public List<ProviderResponse> getProvidersByUserStatus(UserStatus status) {
        return providerRepository.findByStatus(status).stream()
                .map(providerService::mapToProviderResponse)
                .collect(Collectors.toList());
    }

}
