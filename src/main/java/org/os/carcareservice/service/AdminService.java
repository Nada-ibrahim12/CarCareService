package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.mappers.UserMapper;
import org.os.carcareservice.repository.UserRepository;
import org.os.carcareservice.specification.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // ? GET USER BY ID
    public BaseUserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    // ? DELETE USER BY ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    // ? GET ALL USERS OR WITH SPECIFICATION
    public List<BaseUserDTO> getUsers(String name, String role, String status) {
        Specification<User> spec = Specification
                .where(UserSpecification.hasName(name))
                .and(UserSpecification.hasRole(role))
                .and(UserSpecification.hasStatus(status));

        return userRepository
                .findAll(spec)
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }
}

