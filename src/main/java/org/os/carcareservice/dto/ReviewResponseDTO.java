package org.os.carcareservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReviewResponseDTO {
    // getters and setters
    private int reviewId;
    private int rating;
    private String comments;
    private Long customerId;
    private Integer requestId;
    private Integer serviceId;
    private LocalDateTime createdAt;

}
