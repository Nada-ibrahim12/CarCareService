package org.os.carcareservice.controller;

import org.os.carcareservice.entity.Settings;
import org.os.carcareservice.entity.SettingsLog;
import org.os.carcareservice.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings")
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

    // GET /settings/preview → (Admin) Preview changes.
    @GetMapping("/preview")
    public ResponseEntity<Settings> preview(@RequestBody Settings changes) {
        return ResponseEntity.ok(settingsService.preview(changes));
    }

    // GET /settings/logs → (Admin) Get settings change history.
    @GetMapping("/logs")
    public ResponseEntity<List<SettingsLog>> logs() {
        return ResponseEntity.ok(settingsService.logs());
    }
}


