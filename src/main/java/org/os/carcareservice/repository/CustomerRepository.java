package org.os.carcareservice.repository;

import java.util.Optional;

import org.os.carcareservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Optional<Customer> findByToken(String token);
    Optional<Customer> findById(Integer id);
}
