package org.os.carcareservice.service;

import org.os.carcareservice.entity.*;
import org.os.carcareservice.repository.AuditLogRepository;
import org.os.carcareservice.repository.CustomerRepository;
import org.os.carcareservice.repository.ProviderRepository;
import org.os.carcareservice.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {
    private final CustomerRepository customerRepository;
    private final AuditLogRepository auditLogRepository;
    private final ProviderRepository providerRepository;
    private final RequestRepository requestRepository;

    public AdminService(CustomerRepository customerRepository,
                        AuditLogRepository auditLogRepository, ProviderRepository providerRepository, RequestRepository requestRepository) {
        this.customerRepository = customerRepository;
        this.auditLogRepository = auditLogRepository;
        this.providerRepository = providerRepository;
        this.requestRepository = requestRepository;
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
