package org.os.carcareservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProviderRegistrationRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String approvalStatus = "PENDING";

    private String profileDetails;

    private String nationalId;
    private String licenseNumber;
    private LocalDateTime licenseExpiryDate;
    private Integer yearsOfExperience;
    private String specialization;
    private String companyName;
    private Double hourlyRate;
    private Boolean isCertified = false;


}
