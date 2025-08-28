package org.os.carcareservice.dto;

import java.time.LocalDateTime;

public class CustomerCarDTO {

    private Long customerId;
    private Integer carId;
    private String licenseNumber;
    private String plate;
    private Integer carTypeId;
    private LocalDateTime createdAt;

    // Constructors
    public CustomerCarDTO() {}

    public CustomerCarDTO(Long customerId, Integer carId, String licenseNumber, String plate, Integer carTypeId, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.carId = carId;
        this.licenseNumber = licenseNumber;
        this.plate = plate;
        this.carTypeId = carTypeId;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
