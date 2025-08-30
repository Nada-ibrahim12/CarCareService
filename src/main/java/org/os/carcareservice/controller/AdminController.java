package org.os.carcareservice.controller;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.dto.ProviderResponse;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.service.AdminProviderService;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // 🔐 All endpoints require ADMIN role
public class AdminController {

    private final AdminService adminService;
    private final AdminProviderService adminProviderService;

    // ==================================================================================================
    /**
     * 🔹 Get user details by ID
     *
     * Method: GET
     * Endpoint: /api/admin/users/{id}
     * Auth: Bearer Token (ADMIN role)
     *
     * @param id User ID (path variable)
     * @return User details as BaseUserDTO
     *
     * Example:
     *   GET http://localhost:8080/api/admin/users/5
     *
     * Response: 200 OK
     * {
     *   "id": 5,
     *   "name": "John Doe",
     *   "email": "john@example.com",
     *   "role": "CUSTOMER",
     *   "status": "ACTIVE"
     * }
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<BaseUserDTO> getUserById(@PathVariable Long id) {
        BaseUserDTO user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // ==================================================================================================
    /**
     * 🔹 Delete user by ID
     *
     * Method: DELETE
     * Endpoint: /api/admin/users/{id}
     * Auth: Bearer Token (ADMIN role)
     *
     * @param id User ID
     * @return Success message
     *
     * Example:
     *   DELETE http://localhost:8080/api/admin/users/5
     *
     * Response: 200 OK
     * "User deleted successfully"
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // ==================================================================================================
    /**
     * 🔹 Get all users (with optional filters)
     *
     * Method: GET
     * Endpoint: /api/admin/users
     * Auth: Bearer Token (ADMIN role)
     *
     * Query Params (optional):
     * - name (String) → filter by name (case-insensitive)
     * - role (String) → ADMIN | CUSTOMER | PROVIDER
     * - status (String) → ACTIVE | INACTIVE | SUSPENDED | PENDING_VERIFICATION
     *
     * Example:
     *   GET http://localhost:8080/api/admin/users?name=evra&role=customer&status=active
     *
     * Response: 200 OK
     * [
     *   {
     *     "id": 3,
     *     "name": "Evra Smith",
     *     "email": "evra@example.com",
     *     "role": "CUSTOMER",
     *     "status": "ACTIVE"
     *   }
     * ]
     */
    @GetMapping("/users")
    public ResponseEntity<List<BaseUserDTO>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status
    ) {
        List<BaseUserDTO> users = adminService.getUsers(name, role, status);
        return ResponseEntity.ok(users);
    }

    // ==================================================================================================
    /**
     * 🔹 Get all customers
     *
     * Method: GET
     * Endpoint: /api/admin/customers
     * Auth: Bearer Token (ADMIN role)
     *
     * Example:
     *   GET http://localhost:8080/api/admin/customers
     */
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    // ==================================================================================================
    /**
     * 🔹 Get all providers
     *
     * Method: GET
     * Endpoint: /api/admin/providers
     * Auth: Bearer Token (ADMIN role)
     *
     * Example:
     *   GET http://localhost:8080/api/admin/providers
     */
    @GetMapping("/providers")
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<ProviderResponse> providers = adminProviderService.getAllProviders();
        return ResponseEntity.ok(providers);
    }

    // ==================================================================================================
    /**
     * 🔹 Update customer status
     *
     * Method: PUT
     * Endpoint: /api/admin/customers/{id}/status
     * Auth: Bearer Token (ADMIN role)
     *
     * @param id Customer ID
     * @param status UserStatus (ACTIVE | INACTIVE | SUSPENDED | PENDING_VERIFICATION)
     *
     * Example:
     *   PUT http://localhost:8080/api/admin/customers/3/status?status=SUSPENDED
     *
     * Response: 200 OK
     * {
     *   "id": 3,
     *   "name": "Jane Doe",
     *   "status": "SUSPENDED"
     * }
     */
    @PutMapping("/customers/{id}/status")
    public ResponseEntity<Customer> updateCustomerStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {
        Customer updated = adminService.updateCustomerStatus(id, status);
        if (updated == null) {
            return ResponseEntity.notFound().build(); // 404 if customer missing
        }
        return ResponseEntity.ok(updated);
    }

    // ==================================================================================================
    /**
     * 🔹 Get audit logs
     *
     * Method: GET
     * Endpoint: /api/admin/logs
     * Auth: Bearer Token (ADMIN role)
     *
     * Example:
     *   GET http://localhost:8080/api/admin/logs
     */
    @GetMapping("/logs")
    public List<AuditLog> getLogs() {
        return adminService.getLogs();
    }

    // ==================================================================================================
    /**
     * 🔹 Verify a provider
     *
     * Method: PUT
     * Endpoint: /api/admin/providers/{id}/verify
     * Auth: Bearer Token (ADMIN role)
     *
     * @param id Provider ID
     * @param isVerified true | false
     *
     * Example:
     *   PUT http://localhost:8080/api/admin/providers/2/verify?isVerified=true
     *
     * Response: 200 OK
     * {
     *   "id": 2,
     *   "name": "CarFix Pro",
     *   "isVerified": true
     * }
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/providers/{id}/verify")
    public ResponseEntity<ProviderResponse> verifyProvider(
            @PathVariable Long id,
            @RequestParam Boolean isVerified) {
        ProviderResponse response = adminProviderService.verifyProvider(id, isVerified);
        return ResponseEntity.ok(response);
    }

    // ==================================================================================================
    /**
     * 🔹 Update provider status
     *
     * Method: PUT
     * Endpoint: /api/admin/providers/{id}/status
     * Auth: Bearer Token (ADMIN role)
     *
     * @param id Provider ID
     * @param status UserStatus (ACTIVE | INACTIVE | SUSPENDED | PENDING_VERIFICATION)
     *
     * Example:
     *   PUT http://localhost:8080/api/admin/providers/2/status?status=ACTIVE
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/providers/{id}/status")
    public ResponseEntity<ProviderResponse> updateProviderStatus(
            @PathVariable Long id,
            @RequestParam UserStatus status) {
        ProviderResponse response = adminProviderService.updateProviderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    // ==================================================================================================
    /**
     * 🔹 Get providers by user status
     *
     * Method: GET
     * Endpoint: /api/admin/providers/users-status/{status}
     * Auth: Bearer Token (ADMIN role)
     *
     * @param status UserStatus (ACTIVE | INACTIVE | SUSPENDED | PENDING_VERIFICATION)
     *
     * Example:
     *   GET http://localhost:8080/api/admin/providers/users-status/ACTIVE
     */
    @GetMapping("/providers/users-status/{status}")
    public ResponseEntity<List<ProviderResponse>> getProvidersByUserStatus(
            @PathVariable UserStatus status) {
        List<ProviderResponse> providers = adminProviderService.getProvidersByUserStatus(status);
        return ResponseEntity.ok(providers);
    }

    // ==================================================================================================
    /**
     * 🔹 Get completed bookings for a provider
     *
     * Method: GET
     * Endpoint: /api/admin/requests?provider={id}&status=completed
     * Auth: Bearer Token (ADMIN role)
     *
     * @param providerId Provider ID
     * @param status Must be "completed"
     *
     * Example:
     *   GET http://localhost:8080/api/admin/requests?provider=5&status=completed
     */
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
