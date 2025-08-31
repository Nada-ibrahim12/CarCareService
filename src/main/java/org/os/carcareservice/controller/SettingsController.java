package org.os.carcareservice.controller;

import org.os.carcareservice.entity.Settings;
import org.os.carcareservice.entity.SettingsLog;
import org.os.carcareservice.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    // GET /settings → Get platform settings.
    @GetMapping
    public ResponseEntity<Settings> get() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    // PUT /settings → (Admin) Update settings.
    @PutMapping
    public ResponseEntity<Settings> update(@RequestBody Settings updated) {
        return ResponseEntity.ok(settingsService.updateSettings(updated));
    }

    // GET /api/settings/preview → (Admin) Preview changes.
    @GetMapping("/preview")
    public ResponseEntity<Settings> preview(
            @RequestParam(required = false) String platformName,
            @RequestParam(required = false) String logoUrl,
            @RequestParam(required = false) String aboutImage,
            @RequestParam(required = false) String aboutDescription,
            @RequestParam(required = false) String termsAndCondition,
            @RequestParam(required = false) String facebookUrl,
            @RequestParam(required = false) String whatsappNumber,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String secondPhoneNumber) {
        
        Settings changes = new Settings();
        changes.setPlatformName(platformName);
        changes.setLogoUrl(logoUrl);
        changes.setAboutImage(aboutImage);
        changes.setAboutDescription(aboutDescription);
        changes.setTermsAndCondition(termsAndCondition);
        changes.setFacebookUrl(facebookUrl);
        changes.setWhatsappNumber(whatsappNumber);
        changes.setPhoneNumber(phoneNumber);
        changes.setSecondPhoneNumber(secondPhoneNumber);
        
        return ResponseEntity.ok(settingsService.preview(changes));
    }

    // GET /settings/logs → (Admin) Get settings change history.
    @GetMapping("/logs")
    public ResponseEntity<List<SettingsLog>> logs() {
        return ResponseEntity.ok(settingsService.logs());
    }
}


