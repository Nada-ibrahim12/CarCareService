package org.os.carcareservice.dto;

import lombok.Data;
import org.os.carcareservice.entity.UserStatus;

import java.time.LocalDateTime;

@Data
public class ProviderResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserStatus status;
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

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setStatus(UserStatus status) { this.status = status; }
    public void setProfileDetails(String profileDetails) { this.profileDetails = profileDetails; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setLicenseExpiryDate(LocalDateTime licenseExpiryDate) { this.licenseExpiryDate = licenseExpiryDate; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setIsCertified(Boolean isCertified) { this.isCertified = isCertified; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
}
