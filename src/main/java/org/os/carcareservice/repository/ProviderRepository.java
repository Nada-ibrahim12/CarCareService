package org.os.carcareservice.repository;

import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Provider> findById(Long id);
    List<Provider> findByStatus(UserStatus status);
}
