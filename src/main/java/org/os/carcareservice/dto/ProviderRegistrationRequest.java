package org.os.carcareservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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

    @NotBlank(message = "Approval status is mandatory")
    private String approvalStatus = "PENDING";

    private String profileDetails;


}
