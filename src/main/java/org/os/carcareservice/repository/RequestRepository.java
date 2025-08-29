package org.os.carcareservice.repository;

import org.os.carcareservice.entity.Request;
import org.os.carcareservice.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByCustomerId(Long customerId);
    List<Request> findByProviderId(Long providerId);
    @Query("SELECT r FROM Request r WHERE r.status = :status")
    List<Request> findByStatus(@Param("status") RequestStatus status);
    Request save(Request request);
}