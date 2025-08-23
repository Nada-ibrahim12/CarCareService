package org.os.carcareservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private int bookingId;

    private int customerId;

    private int serviceId;

    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comments;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false) // foreign key in review table
    private Request request;

    public Review() {
    }

    public Review(Request request,
                  int bookingId,
                  int customerId,
                  int serviceId,
                  int rating,
                  String comments,
                  LocalDateTime createdAt) {
        this.request = request;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
