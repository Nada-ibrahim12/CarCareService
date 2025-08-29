package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.AvailabilityRequest;
import org.os.carcareservice.dto.AvailabilityResponse;
import org.os.carcareservice.entity.Availability;
import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.repository.AvailabilityRepository;
import org.os.carcareservice.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final ProviderRepository providerRepository;

    @Override
    public List<AvailabilityResponse> getProviderAvailability(Long providerId) {

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found with id: " + providerId));

        List<Availability> availabilityList = availabilityRepository.findByProvider(provider);
        return availabilityList.stream()
                .map(this::mapToAvailabilityResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<AvailabilityResponse> updateProviderAvailability(Long providerId, List<AvailabilityRequest> availabilityRequests) {

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found with id: " + providerId));

        availabilityRepository.deleteByProviderId(providerId);

        List<Availability> newAvailability = availabilityRequests.stream()
                .map(request -> {
                    Availability availability = new Availability();
                    availability.setProvider(provider);
                    availability.setDayOfWeek(request.getDayOfWeek());
                    availability.setStartTime(request.getStartTime());
                    availability.setEndTime(request.getEndTime());
                    availability.setIsAvailable(request.getIsAvailable());
                    return availability;
                })
                .collect(Collectors.toList());

        List<Availability> savedAvailability = availabilityRepository.saveAll(newAvailability);

        return savedAvailability.stream()
                .map(this::mapToAvailabilityResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProviderAvailability(Long providerId) {
        availabilityRepository.deleteByProviderId(providerId);
    }

    private AvailabilityResponse mapToAvailabilityResponse(Availability availability) {
        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(availability.getId());
        response.setDayOfWeek(availability.getDayOfWeek());
        response.setStartTime(availability.getStartTime());
        response.setEndTime(availability.getEndTime());
        response.setIsAvailable(availability.getIsAvailable());
        return response;
    }
}