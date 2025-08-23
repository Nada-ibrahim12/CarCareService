package org.os.carcareservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id", nullable = false)
    private Integer providerId;

    @Column(name = "approval_status", nullable = false, length = 50)
    private String approvalStatus;

    @Lob
    @Column(name = "profile_details", columnDefinition = "TEXT")
    private String profileDetails;

    public Provider() {
    }

    public Provider(String approvalStatus, String profileDetails) {
        this.approvalStatus = approvalStatus;
        this.profileDetails = profileDetails;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

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
}
