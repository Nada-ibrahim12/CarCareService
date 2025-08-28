package org.os.carcareservice.controller;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users/{id}")
    public ResponseEntity<BaseUserDTO> getUserById(@PathVariable Long id) {
        BaseUserDTO user = adminService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
