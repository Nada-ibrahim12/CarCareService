package org.os.carcareservice.dto;

import java.time.LocalDateTime;
import org.os.carcareservice.entity.Role;

// Read model for GET /admin/logs

public record AuditLogResponse(
        Long id,
        String action,
        LocalDateTime createdAt,
        Long actorUserId,
        Role actorRole,
        String targetType,
        Long targetId,
        String details) {
}
