package org.os.carcareservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationDTO {
    // Getters and Setters
    private int notificationId;
    private String message;
    private String type;
    private String status;
    private LocalDateTime createdAt;
    private Long userId;

}
