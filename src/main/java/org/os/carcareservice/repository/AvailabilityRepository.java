package org.os.carcareservice.repository;

import org.os.carcareservice.entity.Availability;
import org.os.carcareservice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;


@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByProviderId(Long providerId);
    List<Availability> findByProvider(Provider provider);
    Optional<Availability> findByProviderIdAndDayOfWeek(Long providerId, DayOfWeek dayOfWeek);
    void deleteByProviderId(Long providerId);
}