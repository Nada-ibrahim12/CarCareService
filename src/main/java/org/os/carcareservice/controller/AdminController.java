package org.os.carcareservice.controller;

import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.service.AdminProviderService;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminProviderService adminProviderService;

    public AdminController(AdminService adminService, AdminProviderService adminProviderService) {
        this.adminService = adminService;
        this.adminProviderService = adminProviderService;
    }

    // GET /admin/customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
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

    @GetMapping("/providers")
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = adminProviderService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/providers/users-status/{status}")
    public ResponseEntity<List<ProviderResponse>> getProvidersByUserStatus(
            @PathVariable UserStatus status) {

        List<ProviderResponse> providers = adminProviderService.getProvidersByUserStatus(status);
        return ResponseEntity.ok(providers);
    }
}
