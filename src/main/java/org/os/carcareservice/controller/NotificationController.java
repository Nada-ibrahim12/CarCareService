package org.os.carcareservice.controller;

import org.os.carcareservice.dto.BroadcastRequestDTO;
import org.os.carcareservice.dto.NotificationDTO;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


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
    @GetMapping("/user")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Long userId = currentUser.getId();
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    //Done
    @PutMapping("/user/{notificationId}/mark-as-read")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(
            Authentication authentication,
            @PathVariable Integer notificationId) {
        User currentUser = (User) authentication.getPrincipal();
        Long userId = currentUser.getId();
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/broadcast")
    public ResponseEntity<List<NotificationDTO>> broadcastNotification(
            @RequestBody BroadcastRequestDTO request) {

        List<NotificationDTO> notifications =
                notificationService.broadcastNotification(request.getMessage(), request.getType());

        return ResponseEntity.ok(notifications);
    }

    //Done
    /* how to use the notify customer endpoint
            POST /api/notifications/customer/{id}
        {
            "message": "Your service request has been approved!",
            "type": "REQUEST_UPDATE"
        }

     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROVIDER')")
    @PostMapping("/customer/{id}")
    public ResponseEntity<NotificationDTO> notifyCustomer(
            @PathVariable Long id,
            @RequestBody BroadcastRequestDTO request) {
        NotificationDTO notification =
                notificationService.notifyCustomer(id, request.getMessage(), request.getType());

        return ResponseEntity.ok(notification);
    }

    //Done
    /* how to use the notify provider endpoint
            POST /api/notifications/provider/{id}
        {
            "message": "Your service request has been approved!",
            "type": "REQUEST_UPDATE"
        }

     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    @PostMapping("/provider/{id}")
    public ResponseEntity<NotificationDTO> notifyProvider(
            @PathVariable Long id,
            @RequestBody BroadcastRequestDTO request) {

        NotificationDTO notification =
                notificationService.notifyProvider(id, request.getMessage(), request.getType());

        return ResponseEntity.ok(notification);
    }

    //Done
    /* how to use the notify customers endpoint
        POST /api/notifications/customers
    {
        "message": "Your service request has been approved!",
        "type": "Customers-Message"
    }
 */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/customers")
    public ResponseEntity<String> notifyCustomers(@RequestBody NotificationDTO dto) {
        notificationService.notifyAllCustomers(dto);
        return ResponseEntity.ok("Notification sent to all customers");
    }

    //Done
    /* how to use the notify providers endpoint
        POST /api/notifications/providers
    {
        "message": "Your service request has been approved!",
        "type": "Providers-Message"
    }
 */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/providers")
    public ResponseEntity<String> notifyProviders(@RequestBody NotificationDTO dto) {
        notificationService.notifyAllProviders(dto);
        return ResponseEntity.ok("Notification sent to all providers");
    }

    //Done
    /* how to use the notify Admins endpoint
        POST /api/notifications/admins
    {
        "message": "Your service request has been approved!",
        "type": "Admins-Message"
    }
 */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admins")
    public ResponseEntity<String> notifyAdmins(@RequestBody NotificationDTO dto) {
        notificationService.notifyAllAdmins(dto);
        return ResponseEntity.ok("Notification sent to all Admins");
    }

}
