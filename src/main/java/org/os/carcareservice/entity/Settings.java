package org.os.carcareservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_id", nullable = false)
    private Integer settingsId;

    @Column(name = "platform_name", nullable = false, length = 100)
    private String platformName;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "about_image")
    private String aboutImage;

    @Column(name = "about_description", columnDefinition = "TEXT")
    private String aboutDescription;

    @Column(name = "terms_and_condition", columnDefinition = "TEXT")
    private String termsAndCondition;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "whatsapp_number")
    private String whatsappNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "second_phone_number")
    private String secondPhoneNumber;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Settings() {}

    public Settings(String platformName, String logoUrl, String aboutImage, String aboutDescription,
                    String termsAndCondition, String facebookUrl, String whatsappNumber,
                    String phoneNumber, String secondPhoneNumber, LocalDateTime updatedAt) {
        this.platformName = platformName;
        this.logoUrl = logoUrl;
        this.aboutImage = aboutImage;
        this.aboutDescription = aboutDescription;
        this.termsAndCondition = termsAndCondition;
        this.facebookUrl = facebookUrl;
        this.whatsappNumber = whatsappNumber;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Integer getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(Integer settingsId) {
        this.settingsId = settingsId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAboutImage() {
        return aboutImage;
    }

    public void setAboutImage(String aboutImage) {
        this.aboutImage = aboutImage;
    }

    public String getAboutDescription() {
        return aboutDescription;
    }

    public void setAboutDescription(String aboutDescription) {
        this.aboutDescription = aboutDescription;
    }

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
