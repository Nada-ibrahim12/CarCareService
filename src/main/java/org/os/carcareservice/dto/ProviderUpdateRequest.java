package org.os.carcareservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProviderUpdateRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    private String profileDetails;
}
