package org.os.carcareservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_type")
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_type_id", nullable = false)
    private Integer carTypeId;

    @Column(name = "car_type_name", nullable = false)
    private String carTypeName;

    public CarType() {}

    public CarType(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }
}
