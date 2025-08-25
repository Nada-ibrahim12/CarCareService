package org.os.carcareservice.entity;

public enum Role {
    ADMIN,
    CUSTOMER,
    PROVIDER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}