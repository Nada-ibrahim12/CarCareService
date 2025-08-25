package org.os.carcareservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider extends User {

    @Column(name = "approval_status", nullable = false, length = 50)
    private String approvalStatus = "PENDING";

    @Column(name = "national_id", unique = true, length = 50)
    private String nationalId;

    @Column(name = "license_number", unique = true, length = 100)
    private String licenseNumber;

    @Column(name = "license_expiry_date")
    private LocalDateTime licenseExpiryDate;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "specialization", length = 200)
    private String specialization;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "is_certified")
    private Boolean isCertified = false;

    @Column(name = "rating")
    private Double rating = 0.0;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @Lob
    @Column(name = "profile_details", columnDefinition = "TEXT")
    private String profileDetails;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Service> services = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin managedByAdmin;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Availability> availability = new ArrayList<>();
    // Constructors
    public Provider() {
        super();
    }

    public Provider(String name, String email, String phone, String password,
                    String approvalStatus, String profileDetails) {
        super(name, email, phone, password);
        this.approvalStatus = approvalStatus;
        this.profileDetails = profileDetails;
    }

    public Provider(String approvalStatus, String profileDetails) {
        this.approvalStatus = approvalStatus;
        this.profileDetails = profileDetails;
    }

    // Override from User
    @Override
    public Role getRole() {
        return Role.PROVIDER;
    }

    // Getters & Setters
    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(String profileDetails) {
        this.profileDetails = profileDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Admin getManagedByAdmin() {
        return managedByAdmin;
    }

    public void setManagedByAdmin(Admin managedByAdmin) {
        this.managedByAdmin = managedByAdmin;
    }

    public void addAvailability(Availability availability) {
        this.availability.add(availability);
        availability.setProvider(this);
    }

    public void removeAvailability(Availability availability) {
        this.availability.remove(availability);
        availability.setProvider(null);
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(LocalDateTime licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Boolean isCertified) {
        this.isCertified = isCertified;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }

    // Utility methods for bidirectional relationships
//    public void addService(Service service) {
//        services.add(service);
//        service.setProvider(this);
//    }
//
//    public void removeService(Service service) {
//        services.remove(service);
//        service.setProvider(null);
//    }
//
//    public void addRequest(Request request) {
//        requests.add(request);
//        request.setProvider(this);
//    }
//
//    public void removeRequest(Request request) {
//        requests.remove(request);
//        request.setProvider(null);
//    }
//
//    public void addNotification(Notification notification) {
//        notifications.add(notification);
//        notification.setProvider(this);
//    }
//
//    public void removeNotification(Notification notification) {
//        notifications.remove(notification);
//        notification.setProvider(null);
//    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", profileDetails='" + profileDetails + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
