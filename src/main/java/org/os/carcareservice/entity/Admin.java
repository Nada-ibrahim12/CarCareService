package org.os.carcareservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
