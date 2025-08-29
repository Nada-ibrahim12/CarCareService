package org.os.carcareservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationDTO {

    private int notificationId;
    private String message;
    private String type;
    private String status;
    private LocalDateTime createdAt;
    private Long userId;

}

