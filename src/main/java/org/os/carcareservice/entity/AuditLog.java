package org.os.carcareservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private LocalDateTime createdAt;
    private Long targetId;
    private String details;

    public AuditLog() {
    }

    public AuditLog(String action, LocalDateTime createdAt, Long targetId, String details) {
        this.action = action;
        this.createdAt = createdAt;
        this.targetId = targetId;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getDetails() {
        return details;
    }
}
