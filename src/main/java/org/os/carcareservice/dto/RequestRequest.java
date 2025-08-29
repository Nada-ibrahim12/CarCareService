package org.os.carcareservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Car ID is required")
    private Integer carId;

    @NotNull(message = "Provider ID is required")
    private Long providerId;

    @NotNull(message = "Service ID is required")
    private Integer serviceId;

    @NotNull(message = "Location is required")
    private String location;

    @NotNull(message = "Estimated cost is required")
    private BigDecimal estimatedCost;

    private String status = "PENDING";

    //Getters and Setters


//    public @NotNull(message = "Car ID is required") Long getCarId() {
//        return carId;
//    }
//    public void setCarId(@NotNull(message = "Car ID is required") Long carId) {
//        this.carId = carId;
//    }
//    public @NotNull(message = "Provider ID is required") Long getProviderId() {
//        return providerId;
//    }
//    public void setProviderId(@NotNull(message = "Provider ID is required") Long providerId) {
//        this.providerId = providerId;
//    }
//    public @NotNull(message = "Service ID is required") Long getServiceId() {
//        return serviceId;
//    }
//    public void setServiceId(@NotNull(message = "Service ID is required") Long serviceId) {
//        this.serviceId = serviceId;
//    }
//    public @NotNull(message = "Location is required") String getLocation() {
//        return location;
//    }
//    public void setLocation(@NotNull String location) {
//        this.location = location;
//    }
//    public @NotNull(message = "Estimated cost is required") BigDecimal getEstimatedCost() {
//        return estimatedCost;
//    }
//    public void setEstimatedCost(@NotNull BigDecimal estimatedCost) {
//        this.estimatedCost = estimatedCost;
//    }
//    public @NotNull(message = "Status is required") String getStatus() {
//        return status;
//    }
//    public void setStatus(@NotNull String status) {
//        this.status = status;
//    }

}