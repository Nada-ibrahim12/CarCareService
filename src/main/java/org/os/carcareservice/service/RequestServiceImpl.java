package org.os.carcareservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.RequestRequest;
import org.os.carcareservice.dto.RequestResponse;
import org.os.carcareservice.dto.StatusHistoryResponse;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.repository.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestStatusChangeRepository statusChangeRepository;

    private final CustomerRepository customerRepository;
    private final CustomerCarRepository customerCarRepository;
    private final ProviderRepository providerRepository;
    private final ServiceService serviceService;

    @Override
    @Transactional
    public RequestResponse createRequest(RequestRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);
        CustomerCar customerCar = customerCarRepository.findById(request.getCarId()).orElse(null);
        Provider provider = providerRepository.findById(request.getProviderId()).orElse(null);
        org.os.carcareservice.entity.Service service = serviceService.getServiceById(request.getServiceId().intValue());

        Request requestt = new Request();
        requestt.setCustomer(customer);
        requestt.setCustomerCar(customerCar);
        requestt.setProvider(provider);
        requestt.setService(service);
        requestt.setLocation(request.getLocation());
        requestt.setEstimatedCost(request.getEstimatedCost());
        requestt.setStatus(RequestStatus.PENDING);
        requestt.setCreatedAt(LocalDateTime.now());
        requestt.setUpdatedAt(LocalDateTime.now());

        Request savedRequest = requestRepository.save(requestt);

        createStatusChange(savedRequest, RequestStatus.PENDING);

        return mapToResponse(savedRequest);
    }
    @Override
    public RequestResponse getRequestById(Integer requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        return mapToResponse(request);
    }

    @Override
    public List<RequestResponse> getCustomerRequests(Long customerId) {
        List<Request> requests = requestRepository.findByCustomerId(customerId);
        return requests.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestResponse> getCustomerRequestsByToken(String token) {
        // Will implement with authentication
        throw new UnsupportedOperationException("Token-based requests not implemented yet");
    }

    @Override
    public List<StatusHistoryResponse> getRequestStatusHistory(Integer requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        return statusChangeRepository.findByRequest_RequestIdOrderByCreatedAtAsc(requestId).stream()
                .map(this::mapToStatusHistoryResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void cancelRequest(Integer requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() == RequestStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel completed request");
        }

        request.setStatus(RequestStatus.CANCELLED);
        request.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(request);

        createStatusChange(request, RequestStatus.CANCELLED);
    }

    @Override
    public List<RequestResponse> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestResponse> getRequestsByStatus(String status) {
        try {
        RequestStatus requestStatus = RequestStatus.valueOf(status.toUpperCase());
        List<Request> requests = requestRepository.findByStatus(requestStatus);

        return requests.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Invalid status: " + status);
    }
    }

    private void createStatusChange(Request request, RequestStatus status) {
        RequestStatusChange statusChange = new RequestStatusChange();
        statusChange.setRequest(request);
        statusChange.setStatus(status);
        statusChange.setCreatedAt(LocalDateTime.now());
        statusChangeRepository.save(statusChange);
    }

    public List<RequestResponse> getProviderRequests(Long providerId) {
        List<Request> requests = requestRepository.findByProviderId(providerId);
        return requests.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RequestResponse updateStatus(Integer requestId, RequestStatus status) {
        Request request = requestRepository.findById(requestId).orElse(null);
        request.setStatus(status);
        request.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(request);
        createStatusChange(request, RequestStatus.values()[status.ordinal()]);
        return mapToResponse(request);
    }

    public List<StatusHistoryResponse> getProviderRequestsHistory(Long providerId) {
        List<StatusHistoryResponse> responses = new ArrayList<>();

        List<Request> requests = requestRepository.findByProviderId(providerId);

        for (Request request : requests) {
            List<StatusHistoryResponse> history = statusChangeRepository
                    .findByRequest_RequestIdOrderByCreatedAtAsc(request.getRequestId())
                    .stream()
                    .map(this::mapToStatusHistoryResponse)
                    .collect(Collectors.toList());

            responses.addAll(history);
        }

        return responses;
    }

    private RequestResponse mapToResponse(Request request) {
        RequestResponse response = new RequestResponse();
        response.setRequestId(request.getRequestId());
        response.setCustomerId(request.getCustomer().getId());
        response.setCustomerName(request.getCustomer().getName());
        response.setCarId(request.getCustomerCar().getCarId());
        response.setCarDetails(request.getCustomerCar().getLicenseNumber() + " - " + request.getCustomerCar().getPlate());
        response.setProviderId(request.getProvider().getId());
        response.setProviderName(request.getProvider().getName());
        response.setServiceId(request.getService().getServiceId());
        response.setServiceName(request.getService().getServiceName());
        response.setLocation(request.getLocation());
        response.setEstimatedCost(request.getEstimatedCost());
        response.setStatus(request.getStatus().toString());
        response.setCreatedAt(request.getCreatedAt());
        response.setUpdatedAt(request.getUpdatedAt());

        // Get status history
        List<StatusHistoryResponse> statusHistory = statusChangeRepository
                .findByRequest_RequestIdOrderByCreatedAtAsc(request.getRequestId()).stream()
                .map(this::mapToStatusHistoryResponse)
                .collect(Collectors.toList());
        response.setStatusHistory(statusHistory);

        return response;
    }

    private StatusHistoryResponse mapToStatusHistoryResponse(RequestStatusChange statusChange) {
        StatusHistoryResponse response = new StatusHistoryResponse();
        response.setStatus(statusChange.getStatus().toString());
        response.setChangedAt(statusChange.getCreatedAt());
        return response;
    }
}