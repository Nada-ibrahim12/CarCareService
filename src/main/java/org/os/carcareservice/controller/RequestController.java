package org.os.carcareservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.RequestRequest;
import org.os.carcareservice.dto.RequestResponse;
import org.os.carcareservice.dto.StatusHistoryResponse;
import org.os.carcareservice.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

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

    @GetMapping("/customer/{custId}")
    public ResponseEntity<List<RequestResponse>> getCustomerRequest(@PathVariable Long custId) {
        List<RequestResponse> responses = requestService.getCustomerRequests(custId);
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
}
