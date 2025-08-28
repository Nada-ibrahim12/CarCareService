package org.os.carcareservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.os.carcareservice.config.JwtService;
import org.os.carcareservice.dto.AuthResponse;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.mappers.UserMapper;
import org.os.carcareservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public BaseUserDTO getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
        return userMapper.toDTO(user);
    }

    public BaseUserDTO updateUserProfile(String email, JsonNode updatedProfile) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateEntityFromJson(updatedProfile, user);

        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }

    public AuthResponse updateEmail(String currentEmail, String newEmail) {
        if (userRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("Email already in use");
        }

        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        user.setLastCredentialsUpdate(Instant.now());
        user.setEmail(newEmail);
        User saved = userRepository.save(user);

        return buildAuthResponse(saved);
    }

    public AuthResponse updatePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password does not match");
        }

        user.setLastCredentialsUpdate(Instant.now());
        user.setPassword(passwordEncoder.encode(newPassword));
        User saved = userRepository.save(user);

        return buildAuthResponse(saved);
    }

    public void deleteProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        userRepository.delete(user);
    }


    // ? Helper Method
    private AuthResponse buildAuthResponse(User user) {
        Map<String, Object> claims = Map.of(
                "role", user.getRole().name(),
                "userId", user.getId(),
                "lastCredentialsUpdate", user.getLastCredentialsUpdate().toEpochMilli()
        );

        String token = jwtService.generateToken(claims, user);
        Instant expiration = jwtService.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getId())
                .expiresAt(expiration)
                .build();
    }


}

