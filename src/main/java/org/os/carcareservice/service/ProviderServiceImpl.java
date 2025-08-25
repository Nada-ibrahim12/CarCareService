package org.os.carcareservice.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.ProviderRegistrationRequest;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.dto.ProviderUpdateRequest;
import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService{
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ProviderResponse registerProvider(ProviderRegistrationRequest request) {
        if (providerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Provider provider = new Provider();
        provider.setName(request.getName());
        provider.setEmail(request.getEmail());
        provider.setPhone(request.getPhone());
        provider.setPassword(passwordEncoder.encode(request.getPassword()));
        provider.setApprovalStatus(request.getApprovalStatus());
        provider.setProfileDetails(request.getProfileDetails());

        provider.setNationalId(request.getNationalId());
        provider.setLicenseNumber(request.getLicenseNumber());
        provider.setLicenseExpiryDate(request.getLicenseExpiryDate());
        provider.setYearsOfExperience(request.getYearsOfExperience());
        provider.setSpecialization(request.getSpecialization());
        provider.setCompanyName(request.getCompanyName());
        provider.setIsCertified(request.getIsCertified());

        Provider savedProvider = providerRepository.save(provider);

        return mapToProviderResponse(savedProvider);
    }

    @Override
    public ProviderResponse getProviderById(Long id) {
        Provider provider = providerRepository.findById(id) .orElseThrow(() -> new RuntimeException("Provider not found with id: " + id));
        return mapToProviderResponse(provider);
    }

    @Override
    public ProviderResponse getProviderByEmail(String email) {
        Provider provider = providerRepository.findByEmail(email) .orElseThrow(() -> new RuntimeException("Provider not found with email: " + email));
        return mapToProviderResponse(provider);
    }

    @Override
    @Transactional
    public ProviderResponse updateProvider(Long id, @Valid ProviderUpdateRequest request) {
        Provider provider = providerRepository.findById(id) .orElseThrow(() -> new RuntimeException("Provider not found with id: " + id));

        provider.setName(request.getName());
        provider.setPhone(request.getPhone());
        provider.setProfileDetails(request.getProfileDetails());

        Provider updatedProvider = providerRepository.save(provider);
        return mapToProviderResponse(provider);
    }

    @Override
    public List<ProviderResponse> getAllProviders() {
        return providerRepository.findAll().stream().map(this::mapToProviderResponse).collect(Collectors.toList());
    }

    private ProviderResponse mapToProviderResponse(Provider provider) {
        ProviderResponse response = new ProviderResponse();
        response.setId(provider.getId());
        response.setName(provider.getName());
        response.setEmail(provider.getEmail());
        response.setPhone(provider.getPhone());
        response.setApprovalStatus(provider.getApprovalStatus());
        response.setProfileDetails(provider.getProfileDetails());
        response.setCreatedAt(provider.getCreatedAt());
        response.setUpdatedAt(provider.getUpdatedAt());

        response.setNationalId(provider.getNationalId());
        response.setLicenseNumber(provider.getLicenseNumber());
        response.setLicenseExpiryDate(provider.getLicenseExpiryDate());
        response.setYearsOfExperience(provider.getYearsOfExperience());
        response.setSpecialization(provider.getSpecialization());
        response.setCompanyName(provider.getCompanyName());
        response.setIsCertified(provider.getIsCertified());
        response.setRating(provider.getRating());
        response.setTotalReviews(provider.getTotalReviews());

        return response;
    }
}
