package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.mappers.UserMapper;
import org.os.carcareservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public BaseUserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}

