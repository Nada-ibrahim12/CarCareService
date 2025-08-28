package org.os.carcareservice.service;

import org.os.carcareservice.dto.ReviewDTO;
import org.os.carcareservice.dto.ReviewResponseDTO;
import org.os.carcareservice.entity.Customer;
import org.os.carcareservice.entity.Request;
import org.os.carcareservice.entity.Review;
import org.os.carcareservice.entity.Service; // entity
import org.os.carcareservice.repository.CustomerRepository;
import org.os.carcareservice.repository.RequestRepository;
import org.os.carcareservice.repository.ReviewRepository;
import org.os.carcareservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final RequestRepository requestRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         CustomerRepository customerRepository,
                         RequestRepository requestRepository,
                         ServiceRepository serviceRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.requestRepository = requestRepository;
        this.serviceRepository = serviceRepository;
    }

    public ReviewResponseDTO addReview(ReviewDTO reviewDTO, Long customerId, Integer requestId, Integer serviceId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + serviceId));

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setComments(reviewDTO.getComments());
        review.setCustomer(customer);
        review.setRequest(request);
        review.setService(service);
        review.setCreatedAt(LocalDateTime.now());

        Review saved = reviewRepository.save(review);

        return mapToResponseDTO(saved);
    }

    private ReviewResponseDTO mapToResponseDTO(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setReviewId(review.getReviewId());
        dto.setRating(review.getRating());
        dto.setComments(review.getComments());
        dto.setCustomerId(review.getCustomer().getId());
        dto.setRequestId(review.getRequest().getRequestId());
        dto.setServiceId(review.getService().getServiceId());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
