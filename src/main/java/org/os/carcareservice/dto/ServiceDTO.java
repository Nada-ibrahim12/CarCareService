package org.os.carcareservice.dto;

import java.math.BigDecimal;

public class ServiceDTO {
    private Integer id;
    private String name;
    private String status;
    private BigDecimal price;
    private Long providerId;

    public ServiceDTO() {}

    public ServiceDTO(Integer id, String name, String status, BigDecimal price, Long providerId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.providerId = providerId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }
}
