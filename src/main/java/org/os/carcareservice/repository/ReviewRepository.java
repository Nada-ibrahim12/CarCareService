package org.os.carcareservice.repository;

import org.os.carcareservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRequest_RequestId(Integer requestId);
    
    Optional<Review> findByCustomer_IdAndRequest_RequestId(Long customerId, Integer requestId);
    
    List<Review> findByRequest_Provider_Id(Long providerId);
    
    @Query("SELECT r FROM Review r WHERE r.request.provider.id = :providerId AND r.rating >= :minRating")
    List<Review> findByProviderAndMinRating(
        @Param("providerId") Long providerId,
        @Param("minRating") Integer minRating
    );
}
