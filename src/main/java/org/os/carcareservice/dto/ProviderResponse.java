package org.os.carcareservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProviderResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String approvalStatus;
    private String profileDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String nationalId;
    private String licenseNumber;
    private LocalDateTime licenseExpiryDate;
    private Integer yearsOfExperience;
    private String specialization;
    private String companyName;
    private Boolean isCertified;
    private Double rating;
    private Integer totalReviews;
}
