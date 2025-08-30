package org.os.carcareservice.controller;

import java.util.List;
import org.os.carcareservice.dto.ReviewDTO;
import org.os.carcareservice.dto.ReviewResponseDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")

public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Create a new review
     * @param reviewDTO Review details
     * @param authentication ID of the customer making the review
     * @param requestId ID of the request being reviewed
     * @param serviceId ID of the service being reviewed
     * @return The created review
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(
            Authentication authentication ,
            @RequestBody ReviewDTO reviewDTO,
            @RequestParam Integer requestId,
            @RequestParam Integer serviceId) {
        User currentUser = (User) authentication.getPrincipal();
        Long userId = currentUser.getId();
        ReviewResponseDTO savedReview = reviewService.addReview(reviewDTO, userId, requestId, serviceId);
        return ResponseEntity.ok(savedReview);
    }

    /**
     * Get the review for a specific request
     * @param requestId The ID of the request to get reviews for
     * @return List of reviews for the specified request
     */
    @GetMapping("reviews/{requestId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByRequestId(@PathVariable Integer requestId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByRequestId(requestId);
        return ResponseEntity.ok(reviews);
    }

    /**
     * Get all reviews for a specific provider
     * @param providerId The ID of the provider to get reviews for
     * @return List of reviews for the specified provider
     */
    @GetMapping("reviews/providers/{providerId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByProviderId(@PathVariable Long providerId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByProviderId(providerId);
        return ResponseEntity.ok(reviews);
    }

    //Done
    /**
     * Get reviews for a specific provider with an optional minimum rating
     * @param provider The ID of the provider (required)
     * @param rating (optional) The minimum rating to filter by (inclusive)
     * @return List of reviews matching the criteria
     */
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(
            @RequestParam Long provider,
            @RequestParam Integer rating) {
        return ResponseEntity.ok(reviewService.getReviewsByProviderAndMinRating(provider, rating));
    }

    /**
     * Delete a review (Admin only)
     * @param id The ID of the review to delete
     * @return 204 No Content if successful, 404 if review not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update a review (Customer can only update their own review)
     * @param id The ID of the review to update
     * @param reviewDTO The updated review details
     * @param authentication The ID of the customer making the request (should match review owner)
     * @return The updated review
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            Authentication authentication,
            @PathVariable int id,
            @RequestBody ReviewDTO reviewDTO) {
        User currentUser = (User) authentication.getPrincipal();
        Long customerId = currentUser.getId();
        ReviewResponseDTO updatedReview = reviewService.updateReview(id, reviewDTO, customerId);
        return ResponseEntity.ok(updatedReview);
    }
}
        
