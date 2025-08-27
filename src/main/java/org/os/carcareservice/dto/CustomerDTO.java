package org.os.carcareservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends BaseUserDTO {
    private String location;

    public CustomerDTO(String name, String email, String phone , String password, String location) {
        super(name, email, phone , password);
        this.location = location;
    }
}

