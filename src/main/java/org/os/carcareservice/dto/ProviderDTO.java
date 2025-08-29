package org.os.carcareservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProviderDTO extends BaseUserDTO {
    private String location;
    private String nationalId;
    private String licenseNumber;
    private LocalDateTime licenseExpiryDate;
    private Integer yearsOfExperience;
    private String specialization;
    private String companyName;
    private Boolean isCertified;
    private String profileDetails;

    public ProviderDTO(String name, String email, String phone, String password, String location, String nationalId, String licenseNumber, LocalDateTime licenseExpiryDate, Integer yearsOfExperience, String specialization, String companyName, Boolean isCertified, String profileDetails) {
        super(name, email, phone, password);
        this.location = location;
        this.nationalId = nationalId;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.yearsOfExperience = yearsOfExperience;
        this.specialization = specialization;
        this.companyName = companyName;
        this.isCertified = isCertified;
        this.profileDetails = profileDetails;
    }
}
