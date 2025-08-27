package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.dto.CustomerDTO;
import org.os.carcareservice.entity.Customer;
import org.os.carcareservice.entity.Role;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.mappers.UserMapper;
import org.os.carcareservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public BaseUserDTO getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
        return userMapper.toDTO(user);
    }
}

