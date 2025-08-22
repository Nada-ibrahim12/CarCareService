package org.os.carcareservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_for_car_type")
public class ServiceForCarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_for_car_type_id", nullable = false)
    private Integer serviceForCarTypeId;

    @ManyToOne
    @JoinColumn(name = "car_type_id", nullable = false)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public ServiceForCarType() {}

    public ServiceForCarType(CarType carType, Service service) {
        this.carType = carType;
        this.service = service;
    }

    public Integer getServiceForCarTypeId() {
        return serviceForCarTypeId;
    }

    public void setServiceForCarTypeId(Integer serviceForCarTypeId) {
        this.serviceForCarTypeId = serviceForCarTypeId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
