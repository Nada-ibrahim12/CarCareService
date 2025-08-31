package org.os.carcareservice.service;

import org.os.carcareservice.entity.Settings;
import org.os.carcareservice.entity.SettingsLog;
import org.os.carcareservice.repository.SettingsLogRepository;
import org.os.carcareservice.repository.SettingsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;
    private final SettingsLogRepository logRepository;

    public SettingsService(SettingsRepository settingsRepository, SettingsLogRepository logRepository) {
        this.settingsRepository = settingsRepository;
        this.logRepository = logRepository;
    }

    public Settings getSettings() {
        return settingsRepository.findAll().stream().findFirst().orElseGet(() -> {
            Settings defaultSettings = new Settings();
            defaultSettings.setPlatformName("CarCare Service Platform");
            defaultSettings.setUpdatedAt(LocalDateTime.now());
            return settingsRepository.save(defaultSettings);
        });
    }

    public Settings updateSettings(Settings updated) {
        Settings existing = getSettings();
        String diff = buildDiff(existing, updated);
        copy(existing, updated);
        existing.setUpdatedAt(LocalDateTime.now());
        Settings saved = settingsRepository.save(existing);

        SettingsLog log = new SettingsLog();
        log.setDiffSummary(diff);
        logRepository.save(log);
        return saved;
    }

    public Settings preview(Settings updated) {
        Settings existing = getSettings();
        Settings preview = new Settings(
                LocalDateTime.now(),
                updated.getSecondPhoneNumber() != null ? updated.getSecondPhoneNumber() : existing.getSecondPhoneNumber(),
                updated.getPhoneNumber() != null ? updated.getPhoneNumber() : existing.getPhoneNumber(),
                updated.getWhatsappNumber() != null ? updated.getWhatsappNumber() : existing.getWhatsappNumber(),
                updated.getFacebookUrl() != null ? updated.getFacebookUrl() : existing.getFacebookUrl(),
                updated.getTermsAndCondition() != null ? updated.getTermsAndCondition() : existing.getTermsAndCondition(),
                updated.getAboutDescription() != null ? updated.getAboutDescription() : existing.getAboutDescription(),
                updated.getAboutImage() != null ? updated.getAboutImage() : existing.getAboutImage(),
                updated.getLogoUrl() != null ? updated.getLogoUrl() : existing.getLogoUrl(),
                updated.getPlatformName() != null ? updated.getPlatformName() : existing.getPlatformName()
        );
        preview.setSettingsId(existing.getSettingsId());
        return preview;
    }

    public List<SettingsLog> logs() {
        return logRepository.findAll();
    }

    private static void copy(Settings target, Settings src) {
        if (src.getPlatformName() != null) target.setPlatformName(src.getPlatformName());
        if (src.getLogoUrl() != null) target.setLogoUrl(src.getLogoUrl());
        if (src.getAboutImage() != null) target.setAboutImage(src.getAboutImage());
        if (src.getAboutDescription() != null) target.setAboutDescription(src.getAboutDescription());
        if (src.getTermsAndCondition() != null) target.setTermsAndCondition(src.getTermsAndCondition());
        if (src.getFacebookUrl() != null) target.setFacebookUrl(src.getFacebookUrl());
        if (src.getWhatsappNumber() != null) target.setWhatsappNumber(src.getWhatsappNumber());
        if (src.getPhoneNumber() != null) target.setPhoneNumber(src.getPhoneNumber());
        if (src.getSecondPhoneNumber() != null) target.setSecondPhoneNumber(src.getSecondPhoneNumber());
    }

    private static String buildDiff(Settings oldS, Settings newS) {
        StringBuilder sb = new StringBuilder();
        appendIfChanged(sb, "platformName", oldS.getPlatformName(), newS.getPlatformName());
        appendIfChanged(sb, "logoUrl", oldS.getLogoUrl(), newS.getLogoUrl());
        appendIfChanged(sb, "aboutImage", oldS.getAboutImage(), newS.getAboutImage());
        appendIfChanged(sb, "aboutDescription", oldS.getAboutDescription(), newS.getAboutDescription());
        appendIfChanged(sb, "termsAndCondition", oldS.getTermsAndCondition(), newS.getTermsAndCondition());
        appendIfChanged(sb, "facebookUrl", oldS.getFacebookUrl(), newS.getFacebookUrl());
        appendIfChanged(sb, "whatsappNumber", oldS.getWhatsappNumber(), newS.getWhatsappNumber());
        appendIfChanged(sb, "phoneNumber", oldS.getPhoneNumber(), newS.getPhoneNumber());
        appendIfChanged(sb, "secondPhoneNumber", oldS.getSecondPhoneNumber(), newS.getSecondPhoneNumber());
        return sb.length() == 0 ? "No changes" : sb.toString();
    }

    private static void appendIfChanged(StringBuilder sb, String field, String oldV, String newV) {
        if (newV != null && (oldV == null || !newV.equals(oldV))) {
            if (!sb.isEmpty()) sb.append("; ");
            sb.append(field).append(": ").append(oldV).append(" -> ").append(newV);
        }
    }
}


