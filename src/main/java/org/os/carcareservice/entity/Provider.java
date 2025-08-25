package org.os.carcareservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider extends User {

    @Column(name = "approval_status", nullable = false, length = 50)
    private String approvalStatus;

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
