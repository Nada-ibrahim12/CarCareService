package org.os.carcareservice.controller;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.*;
import org.os.carcareservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

// ! =========================================================================================

// ? register ADMIN

// ? POST api/auth/register/admin

// ?   {
// ?         "name": "ADMIN",
// ?         "email": "ADMIN@example.com",
// ?         "phone": "01155667788",
// ?         "password": "123456"
// ?   }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody AdminDTO request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }


// ! =========================================================================================

// ? register CUSTOMER

// ? POST api/auth/register/customer

// ?   {
// ?         "name": "customer",
// ?         "email": "customer@example.com",
// ?         "phone": "01155667788",
// ?         "password": "123456",
// ?         "location": "Haram, Giza"
// ?   }

    @PostMapping("/register/customer")
    public ResponseEntity<AuthResponse> registerCustomer(@RequestBody CustomerDTO request) {
        return ResponseEntity.ok(authService.registerCustomer(request));
    }


// ! =========================================================================================

// ? register PROVIDER

// ? POST api/auth/register/provider

// ?   {
// ?         "name": "ProviderExtra",
// ?         "email": "provider2@example.com",
// ?         "phone": "01155667788",
// ?         "password": "123456",
// ?         "location": "Maadi, Cairo",
// ?         "nationalId": "29812345555555",
// ?         "licenseNumber": "ABC-123-456",
// ?         "licenseExpiryDate": "2027-08-01T00:00:00",
// ?         "yearsOfExperience": 10,
// ?         "specialization": "Car Engine Repair",
// ?         "companyName": "AutoFix",
// ?         "isCertified": true,
// ?         "profileDetails": "Expert in car engine repair with over 10 years of experience"
// ?    }

    @PostMapping("/register/provider")
    public ResponseEntity<AuthResponse> registerProvider(@RequestBody ProviderDTO request) {
        return ResponseEntity.ok(authService.registerProvider(request));
    }


// ! =========================================================================================

// ? login (all roles)

// ? POST api/auth/login

// ?     {
// ?          "email": "ephraim@gmail.com",
// ?         "password": "12345678"
// ?     }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
