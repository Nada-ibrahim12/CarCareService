package org.os.carcareservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StatusHistoryResponse {
    private String status;
    private LocalDateTime changedAt;
}
