package org.os.carcareservice.service;

import lombok.RequiredArgsConstructor;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.mappers.UserMapper;
import org.os.carcareservice.repository.UserRepository;
import org.os.carcareservice.specification.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.repository.AuditLogRepository;
import org.os.carcareservice.repository.CustomerRepository;
import org.os.carcareservice.repository.ProviderRepository;
import org.os.carcareservice.repository.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
  
   private final CustomerRepository customerRepository;
    private final AuditLogRepository auditLogRepository;
    private final ProviderRepository providerRepository;
    private final RequestRepository requestRepository;

    public AdminService(CustomerRepository customerRepository,
                        AuditLogRepository auditLogRepository, ProviderRepository providerRepository, RequestRepository requestRepository, UserRepository userRepository, UserMapper userMapper) {
        this.customerRepository = customerRepository;
        this.auditLogRepository = auditLogRepository;
        this.providerRepository = providerRepository;
        this.requestRepository = requestRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


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
  
  // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update customer status
    public Customer updateCustomerStatus(Long id, UserStatus status) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setStatus(status);
                    Customer saved = customerRepository.save(customer);

                    AuditLog log = new AuditLog(
                            "UPDATE_CUSTOMER_STATUS",
                            LocalDateTime.now(),
                            id,
                            "Changed status to " + status);
                    auditLogRepository.save(log);

                    return saved;
                })
                .orElse(null); // instead of throwing custom exception
    }

    // Get all logs
    public List<AuditLog> getLogs() {
        return auditLogRepository.findAll();
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getCompletedBookingsByProvider(Long providerId) {
        return requestRepository.findByProviderIdAndStatus(providerId, "completed");
    }
}
