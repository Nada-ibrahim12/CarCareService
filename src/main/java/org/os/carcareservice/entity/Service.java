package org.os.carcareservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer serviceId;

    @Column(name = "name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "status" , nullable = false , length = 50)
    private String serviceStatus;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal servicePrice;


    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;


    public Service() {}

    public Service(String serviceName, String serviceStatus, BigDecimal servicePrice) {
        this.serviceName = serviceName;
        this.serviceStatus = serviceStatus;
        this.servicePrice = servicePrice;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
