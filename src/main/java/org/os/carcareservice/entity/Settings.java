package org.os.carcareservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_id")
    private Integer settingsId;

    @Column(name = "platform_name", nullable = false)
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

    public Settings() {
    }

    public Settings(LocalDateTime updatedAt,
                    String secondPhoneNumber,
                    String phoneNumber,
                    String whatsappNumber,
                    String facebookUrl,
                    String termsAndCondition,
                    String aboutDescription,
                    String aboutImage,
                    String logoUrl,
                    String platformName) {
        this.updatedAt = updatedAt;
        this.secondPhoneNumber = secondPhoneNumber;
        this.phoneNumber = phoneNumber;
        this.whatsappNumber = whatsappNumber;
        this.facebookUrl = facebookUrl;
        this.termsAndCondition = termsAndCondition;
        this.aboutDescription = aboutDescription;
        this.aboutImage = aboutImage;
        this.logoUrl = logoUrl;
        this.platformName = platformName;
    }

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
