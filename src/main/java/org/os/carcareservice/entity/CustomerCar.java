import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_car")
public class CustomerCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "license_number", nullable = false, length = 50, unique = true)
    private String licenseNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Many cars belong to one car type
    @ManyToOne
    @JoinColumn(name = "carTypeID", nullable = false)
    private CarType carType;

    // Getters & Setters
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
