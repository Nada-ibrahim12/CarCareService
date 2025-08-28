package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.*;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.repository.UserRepository;
import org.os.carcareservice.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ? REGISTER ADMIN
    public AuthResponse registerAdmin(AdminDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Admin admin = new Admin(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(admin);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", admin.getRole().name());
        claims.put("userId", admin.getId());
        claims.put("lastCredentialsUpdate", admin.getLastCredentialsUpdate().getEpochSecond());

        String token = jwtService.generateToken( claims , admin);
        Instant expiresAt = jwtService.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .role(admin.getRole().name())
                .email(admin.getEmail())
                .name(admin.getName())
                .userId(admin.getId())
                .expiresAt(expiresAt)
                .build();
    }

    // ? REGISTER CUSTOMER
    public AuthResponse registerCustomer(CustomerDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword())
        );
        // set optional customer fields
        customer.setLocation(request.getLocation());

        userRepository.save(customer);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", customer.getRole().name());
        claims.put("userId", customer.getId());
        claims.put("lastCredentialsUpdate", customer.getLastCredentialsUpdate().getEpochSecond());

        String token = jwtService.generateToken(claims , customer);
        Instant expiresAt = jwtService.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .role(customer.getRole().name())
                .email(customer.getEmail())
                .name(customer.getName())
                .userId(customer.getId())
                .expiresAt(expiresAt)
                .build();
    }

    // ? REGISTER PROVIDER
    public AuthResponse registerProvider(ProviderDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Provider provider = new Provider(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword()),
                UserStatus.PENDING_VERIFICATION,
                request.getProfileDetails()
        );
        // fill provider-specific fields
        provider.setLocation(request.getLocation());
        provider.setNationalId(request.getNationalId());
        provider.setLicenseNumber(request.getLicenseNumber());
        provider.setLicenseExpiryDate(request.getLicenseExpiryDate());
        provider.setYearsOfExperience(request.getYearsOfExperience());
        provider.setSpecialization(request.getSpecialization());
        provider.setCompanyName(request.getCompanyName());
        provider.setIsCertified(request.getIsCertified());

        userRepository.save(provider);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", provider.getRole().name());
        claims.put("userId", provider.getId());
        claims.put("lastCredentialsUpdate", provider.getLastCredentialsUpdate().getEpochSecond());

        String token = jwtService.generateToken(claims , provider);
        Instant expiresAt = jwtService.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .role(provider.getRole().name())
                .email(provider.getEmail())
                .name(provider.getName())
                .userId(provider.getId())
                .expiresAt(expiresAt)
                .build();
    }

    // ? LOGIN (works for all roles)
    public AuthResponse login(LoginRequest request) {
        // authenticate credentials (will throw if invalid)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());
        claims.put("lastCredentialsUpdate", user.getLastCredentialsUpdate().getEpochSecond());

        String token = jwtService.generateToken(claims , user);
        Instant expiresAt = jwtService.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getId())
                .expiresAt(expiresAt)
                .build();
    }
}
