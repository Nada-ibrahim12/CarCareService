package org.os.carcareservice.controller;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

// ! ==================================================================================================

    // ? GET USER BY ID
    @GetMapping("/users/{id}")
    public ResponseEntity<BaseUserDTO> getUserById(@PathVariable Long id) {
        BaseUserDTO user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

// ! ==================================================================================================

    // ? DELETE USER BY ID
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
    @GetMapping("/users")
    public ResponseEntity<List<BaseUserDTO>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status
    ) {
        List<BaseUserDTO> users = adminService.getUsers(name, role, status);
        return ResponseEntity.ok(users);
    }
}
