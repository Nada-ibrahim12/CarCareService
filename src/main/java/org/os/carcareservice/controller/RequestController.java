package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.RequestRequest;
import org.os.carcareservice.dto.RequestResponse;
import org.os.carcareservice.dto.StatusHistoryResponse;
import org.os.carcareservice.entity.RequestStatus;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<RequestResponse> createRequest(
            @Valid
            @RequestBody
            RequestRequest request
    ) {
        RequestResponse response = requestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getRequest(@PathVariable int id) {
        RequestResponse response = requestService.getRequestById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer")
    public ResponseEntity<List<RequestResponse>> getCustomerRequest(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Long userId = currentUser.getId();
        List<RequestResponse> responses = requestService.getCustomerRequests(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}/status-history")
    public ResponseEntity<List<StatusHistoryResponse>> getRequestStatusHistory(@PathVariable int id) {
        List<StatusHistoryResponse> responses = requestService.getRequestStatusHistory(id);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestResponse> deleteRequest(@PathVariable int id) {
        requestService.cancelRequest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<RequestResponse>> getAllRequests() {
        List<RequestResponse> responses = requestService.getAllRequests();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RequestResponse>> getRequestsByStatus(@PathVariable String status) {
        List<RequestResponse> responses = requestService.getRequestsByStatus(status);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RequestResponse> updateRequestStatus(
            @PathVariable int id,
            @RequestBody RequestStatus status) {
        RequestResponse response = requestService.updateStatus(id, status);
        return ResponseEntity.ok(response);
    }

}
