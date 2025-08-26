package org.os.carcareservice.controller;

import org.os.carcareservice.dto.BroadcastRequestDTO;
import org.os.carcareservice.dto.NotificationDTO;
import org.os.carcareservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //Done
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable int notificationId) {
        NotificationDTO notification = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notification);
    }

    //Done
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    //Done
    @PutMapping("/user/{userId}/{notificationId}/mark-as-read")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(
            @PathVariable Long userId,
            @PathVariable Integer notificationId) {
        NotificationDTO updatedNotification = notificationService.markNotificationAsRead(userId, notificationId);
        return ResponseEntity.ok(updatedNotification);
    }

    //Done
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }

    //Done
    /* How to use the broadcast endpoint
    * Content-Type: application/json
    * {
         "message": "System maintenance will occur at 10 PM.",
         "type": "SYSTEM_ALERT"
      }
      */
    @PostMapping("/broadcast")
    public ResponseEntity<List<NotificationDTO>> broadcastNotification(
            @RequestBody BroadcastRequestDTO request) {

        List<NotificationDTO> notifications =
                notificationService.broadcastNotification(request.getMessage(), request.getType());

        return ResponseEntity.ok(notifications);
    }




}
