package org.os.carcareservice.controller;


import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.service.AdminProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final AdminProviderService adminProviderService;

// ! ==================================================================================================

// ? GET USER BY ID

// ? GET api/admin/users/{id}

    @GetMapping("/users/{id}")
    public ResponseEntity<BaseUserDTO> getUserById(@PathVariable Long id) {
        BaseUserDTO user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

// ! ==================================================================================================

// ? DELETE USER BY ID

// ? DELETE api/admin/users/{id}

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

// ! ==================================================================================================

// ? GET ALL USERS OR WITH SPECIFICATION

// ? name, role, status
// ? role can be ADMIN, CUSTOMER, PROVIDER
// ? status can be ACTIVE, INACTIVE , SUSPENDED and PENDING_VERIFICATION
// ? if no params are provided, all users will be returned
// ? if params are provided, users will be filtered by the params ( case-insensitive )

// ? EXAMPLE: http://localhost:8080/api/admin/users?name=EvRa&role=CusTomer&status=aCtiVe

// ? GET api/admin/users

    @GetMapping("/users")
    public ResponseEntity<List<BaseUserDTO>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status
    ) {
        List<BaseUserDTO> users = adminService.getUsers(name, role, status);
        return ResponseEntity.ok(users);

    }


    // GET /admin/customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }
  
   @GetMapping("/providers")
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = adminProviderService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    // PUT /admin/customers/{id}/status
    @PutMapping("/customers/{id}/status")
    public ResponseEntity<Customer> updateCustomerStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {
        Customer updated = adminService.updateCustomerStatus(id, status);
        if (updated == null) {
            return ResponseEntity.notFound().build(); // returns 404 if customer missing
        }
        return ResponseEntity.ok(updated);
    }

    // GET /admin/logs
    @GetMapping("/logs")
    public List<AuditLog> getLogs() {
        return adminService.getLogs();
    }

    @PutMapping("/providers/{id}/verify")
    public ResponseEntity<ProviderResponse> verifyProvider(
            @PathVariable Long id,
            @RequestParam Boolean isVerified) {

        ProviderResponse response = adminProviderService.verifyProvider(id, isVerified);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/providers/{id}/status")
    public ResponseEntity<ProviderResponse> updateProviderStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {

        ProviderResponse response = adminProviderService.updateProviderStatus(id, status);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/providers/users-status/{status}")
    public ResponseEntity<List<ProviderResponse>> getProvidersByUserStatus(
            @PathVariable UserStatus status) {

        List<ProviderResponse> providers = adminProviderService.getProvidersByUserStatus(status);
        return ResponseEntity.ok(providers);
    }

    // /requests?provider=5&status=completed
    @GetMapping("/requests")
    public List<Request> getBookings(
            @RequestParam("provider") Long providerId,
            @RequestParam("status") String status) {

        if ("completed".equalsIgnoreCase(status)) {
            return adminService.getCompletedBookingsByProvider(providerId);
        } else {
            throw new IllegalArgumentException("Only completed status is supported");
        }
    }
}
