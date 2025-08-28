package org.os.carcareservice.specification;

import org.os.carcareservice.entity.*;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<User> hasRole(String roleStr) {
        return (root, query, cb) -> {
            if (roleStr == null) {
                return cb.conjunction();
            }

            Role role;
            try {
                role = Role.valueOf(roleStr.toUpperCase());
            }
            catch (IllegalArgumentException e){
                return cb.disjunction();
            }

            return switch (role) {
                case ADMIN -> cb.equal(root.type(), Admin.class);
                case CUSTOMER -> cb.equal(root.type(), Customer.class);
                case PROVIDER -> cb.equal(root.type(), Provider.class);
            };
        };
    }

    public static Specification<User> hasStatus(String status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }
}
