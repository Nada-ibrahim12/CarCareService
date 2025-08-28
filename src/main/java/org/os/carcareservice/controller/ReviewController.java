package org.os.carcareservice.controller;

import org.os.carcareservice.dto.ReviewDTO;
import org.os.carcareservice.dto.ReviewResponseDTO;
import org.os.carcareservice.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Done
    /* how to use this endpoint
    * api/reviews
    * method: POST --> URL : http://localhost:8080/api/reviews?customerId={X}&requestId={X}&serviceId={X}
    * request body: ReviewDTO
    * request params: customerId, requestId, serviceId
    * response: ReviewResponseDTO
    *
    * */
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(
            @RequestBody ReviewDTO reviewDTO,
            @RequestParam Long customerId,
            @RequestParam Integer requestId,
            @RequestParam Integer serviceId) {

        ReviewResponseDTO savedReview = reviewService.addReview(reviewDTO, customerId, requestId, serviceId);
        return ResponseEntity.ok(savedReview);
    }

}
