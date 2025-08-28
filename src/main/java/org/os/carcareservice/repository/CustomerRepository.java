package org.os.carcareservice.repository;

import java.util.Optional;

import org.os.carcareservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Optional<Customer> findByToken(String token);
    Optional<Customer> findById(Long id);
}
