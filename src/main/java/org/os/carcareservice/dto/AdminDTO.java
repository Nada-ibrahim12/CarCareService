package org.os.carcareservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminDTO extends BaseUserDTO {
    // ? No extra fields for now
    public AdminDTO(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }
}

