package org.os.carcareservice.service;

import jakarta.transaction.Transactional;
import org.os.carcareservice.dto.NotificationDTO;
import org.os.carcareservice.entity.*;
import org.os.carcareservice.exception.NotFoundException;
import org.os.carcareservice.repository.NotificationRepository;
import org.os.carcareservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public NotificationDTO getNotificationById(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new NotFoundException("Notification not found with id: " + notificationId));
        return convertToDTO(notification);
    }
    

    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
            
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        
        if (notifications.isEmpty()) {
            throw new NotFoundException("No notifications found for user with id: " + userId);
        }
        
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO markNotificationAsRead(Long userId, Integer notificationId) {

        userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        
        Notification notification = notificationRepository.findByNotificationIdAndUserId(notificationId, userId)
                .orElseThrow(() -> new NotFoundException("Notification not found with id: " + notificationId + " for user id: " + userId));
        
        notification.setStatus("Read");
        Notification updatedNotification = notificationRepository.save(notification);
        
        return convertToDTO(updatedNotification);
    }

    @Transactional
    public void deleteNotification(Integer notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotFoundException("Notification not found with id: " + notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }



    @Transactional
    public List<NotificationDTO> broadcastNotification(String message, String type) {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("No users found to send broadcast notification.");
        }

        List<Notification> notifications = users.stream()
                .map(user -> new Notification(
                        user,
                        message,
                        type,
                        "Unread",
                        LocalDateTime.now()
                ))
                .toList();

        List<Notification> savedNotifications = notificationRepository.saveAll(notifications);

        return savedNotifications.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationId(notification.getNotificationId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setStatus(notification.getStatus());
        dto.setCreatedAt(notification.getCreatedAt());
        if (notification.getUser() != null) {
            dto.setUserId(notification.getUser().getId());
        }
        return dto;
    }


    @Transactional
    public NotificationDTO notifyCustomer(Long customerId, String message, String type) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId));

        if (!(customer instanceof Customer)) {
            throw new NotFoundException("User with id " + customerId + " is not a customer.");
        }

        Notification notification = new Notification(
                customer,
                message,
                type,
                "Unread",
                LocalDateTime.now()
        );

        Notification saved = notificationRepository.save(notification);
        return convertToDTO(saved);
    }


    @Transactional
    public NotificationDTO notifyProvider(Long providerId, String message, String type) {
        User provider = userRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException("Provider not found with id: " + providerId));

        if (!(provider instanceof Provider)) {
            throw new NotFoundException("User with id " + providerId + " is not a provider.");
        }

        Notification notification = new Notification(
                provider,
                message,
                type,
                "Unread",
                LocalDateTime.now()
        );

        Notification saved = notificationRepository.save(notification);
        return convertToDTO(saved);
    }

    public void notifyAllCustomers(NotificationDTO dto) {
        List<User> customers = userRepository.findByRole(Role.CUSTOMER);
        selectUserType(dto, customers);
    }

    public void notifyAllProviders(NotificationDTO dto) {
        List<User> providers = userRepository.findByRole(Role.PROVIDER);
        selectUserType(dto, providers);
    }
    public void notifyAllAdmins(NotificationDTO dto) {
        List<User> admins = userRepository.findByRole(Role.ADMIN);
        selectUserType(dto, admins);
    }

    private void selectUserType(NotificationDTO dto, List<User> users) {
        for (User user : users) {
            Notification notification = new Notification();
            notification.setMessage(dto.getMessage());
            notification.setType(dto.getType());
            notification.setStatus("UNREAD");
            notification.setCreatedAt(LocalDateTime.now());
            notification.setUser(user);
            notificationRepository.save(notification);
        }
    }

}
