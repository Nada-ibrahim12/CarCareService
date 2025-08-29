package org.os.carcareservice.dto;

import jakarta.validation.constraints.NotNull;
import org.os.carcareservice.entity.UserStatus;

// Request body for PUT /admin/customers/{id}/status
// Example JSON: { "status": "SUSPENDED" }

public record UpdateStatusRequest(
        @NotNull UserStatus status) {
}
