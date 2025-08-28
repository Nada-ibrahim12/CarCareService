package org.os.carcareservice.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import org.os.carcareservice.dto.AdminDTO;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.dto.CustomerDTO;
import org.os.carcareservice.dto.ProviderDTO;
import org.os.carcareservice.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public BaseUserDTO toDTO(User user) {
        return switch (user.getRole()) {
            case Role.CUSTOMER -> {
                Customer c = (Customer) user;
                yield new CustomerDTO(c.getName(), c.getEmail(), c.getPhone(), null, c.getLocation());
            }
            case Role.PROVIDER -> {
                Provider p = (Provider) user;
                yield new ProviderDTO(
                        p.getName(), p.getEmail(), p.getPhone(), null, p.getLocation(),
                        p.getNationalId(), p.getLicenseNumber(), p.getLicenseExpiryDate(),
                        p.getYearsOfExperience(), p.getSpecialization(), p.getCompanyName(),
                        p.getIsCertified(), p.getProfileDetails()
                );
            }
            case Role.ADMIN -> {
                Admin a = (Admin) user;
                yield new AdminDTO(a.getName(), a.getEmail(), a.getPhone(), null);
            }
        };
    }


    public void updateEntityFromJson(JsonNode json, User user) {
        if (json.has("name")) {
            user.setName(json.get("name").asText());
        }
        if (json.has("phone")) {
            user.setPhone(json.get("phone").asText());
        }

        if (user instanceof Customer) {
            if (json.has("location")) {
                ((Customer) user).setLocation(json.get("location").asText());
            }
        }
        else if (user instanceof Provider provider) {

            if (json.has("location")) {
                provider.setLocation(json.get("location").asText());
            }
            if (json.has("nationalId")) {
                provider.setNationalId(json.get("nationalId").asText());
            }
            if (json.has("licenseNumber")) {
                provider.setLicenseNumber(json.get("licenseNumber").asText());
            }
            if (json.has("licenseExpiryDate")) {
                provider.setLicenseExpiryDate(LocalDateTime.parse(json.get("licenseExpiryDate").asText()));
            }
            if (json.has("yearsOfExperience")) {
                provider.setYearsOfExperience(json.get("yearsOfExperience").asInt());
            }
            if (json.has("specialization")) {
                provider.setSpecialization(json.get("specialization").asText());
            }
            if (json.has("companyName")) {
                provider.setCompanyName(json.get("companyName").asText());
            }
            if (json.has("isCertified")) {
                provider.setIsCertified(json.get("isCertified").asBoolean());
            }
            if (json.has("profileDetails")) {
                provider.setProfileDetails(json.get("profileDetails").asText());
            }
        }
    }

}

