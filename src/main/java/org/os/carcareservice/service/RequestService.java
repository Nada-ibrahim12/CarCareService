package org.os.carcareservice.service;

import org.os.carcareservice.dto.RequestRequest;
import org.os.carcareservice.dto.RequestResponse;
import org.os.carcareservice.dto.StatusHistoryResponse;
import org.os.carcareservice.entity.RequestStatus;
import org.os.carcareservice.entity.RequestStatusChange;

import java.util.List;

public interface RequestService {
    RequestResponse createRequest(RequestRequest request);
    RequestResponse getRequestById(Integer requestId);
    List<RequestResponse> getCustomerRequests(Long customerId);
    List<RequestResponse> getCustomerRequestsByToken(String token); // will implement later with auth
    List<StatusHistoryResponse> getRequestStatusHistory(Integer requestId);
    void cancelRequest(Integer requestId);
    List<RequestResponse> getAllRequests();
    List<RequestResponse> getRequestsByStatus(String status);
    List<RequestResponse> getProviderRequests(String email);
//    List<RequestResponse> getProviderRequestsByToken(String token);
    RequestResponse updateStatus(Integer requestId, RequestStatus status);
    List<StatusHistoryResponse> getProviderRequestsHistory(String email);
}