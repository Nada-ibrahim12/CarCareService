package org.os.carcareservice.controller;

import org.os.carcareservice.entity.*;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
}
