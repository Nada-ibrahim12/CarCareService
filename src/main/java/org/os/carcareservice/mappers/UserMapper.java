package org.os.carcareservice.mappers;

import org.os.carcareservice.dto.AdminDTO;
import org.os.carcareservice.dto.BaseUserDTO;
import org.os.carcareservice.dto.CustomerDTO;
import org.os.carcareservice.dto.ProviderDTO;
import org.os.carcareservice.entity.*;
import org.springframework.stereotype.Component;

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
            default -> throw new RuntimeException("Unknown user type");
        };
    }
}

