package org.os.carcareservice.dto;

public class CustomerCarDTO {

    private String licenseNumber;
   

    private Integer carTypeId;



    // Getters & Setters
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public CustomerCarDTO(Integer carTypeId, String licenseNumber) {
        this.carTypeId = carTypeId;
        this.licenseNumber = licenseNumber;
    }


   
}
