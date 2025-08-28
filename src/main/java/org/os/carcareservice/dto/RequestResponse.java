package org.os.carcareservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestResponse {
    private Integer requestId;
    private Long customerId;
    private Integer carId;
    private Long providerId;
    private Integer serviceId;
    private String location;
    private BigDecimal estimatedCost;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<StatusHistoryResponse> statusHistory;

    private String customerName;
    private String providerName;
    private String serviceName;
    private String carDetails;
}
