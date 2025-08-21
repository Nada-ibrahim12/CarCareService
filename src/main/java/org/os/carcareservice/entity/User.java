package org.os.carcareservice.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(nullable = false)

    private String name;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)

    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    @Column
    private String phone;

    @NotBlank
    @Size(min = 8)
    @Column(nullable = false)

    private String password;

    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }

    @Enumerated(EnumType.STRING)

    @Column(nullable = false)

    private UserStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
