package org.os.carcareservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseRegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
}

