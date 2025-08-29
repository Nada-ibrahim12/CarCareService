package org.os.carcareservice.dto;

import org.os.carcareservice.entity.UserStatus;

// small, safe projection for admin listings.

public record CustomerSummary(
        Long id,
        String name,
        String email,
        String phone,
        UserStatus status) {
}
