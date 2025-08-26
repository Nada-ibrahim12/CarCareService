package org.os.carcareservice.service;

import jakarta.transaction.Transactional;
import org.os.carcareservice.dto.NotificationDTO;
import org.os.carcareservice.entity.Notification;
import org.os.carcareservice.exception.NotFoundException;
import org.os.carcareservice.repository.NotificationRepository;
import org.os.carcareservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
}
