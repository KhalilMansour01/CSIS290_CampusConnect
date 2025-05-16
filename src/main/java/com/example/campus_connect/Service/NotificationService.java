package com.example.campus_connect.Service;

import com.example.campus_connect.Entity.NotificationEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(NotificationEntity notification) {
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<NotificationEntity> getUserNotifications(UsersEntity user) {
        return notificationRepository.findByRecipientOrderByCreatedAtDesc(user);
    }

    public long countUnreadNotifications(UsersEntity user) {
        return notificationRepository.countByRecipientAndIsReadFalse(user);
    }

    public void markAsRead(NotificationEntity notification) {
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void markAllAsRead(UsersEntity user) {
        List<NotificationEntity> unread = notificationRepository.findByRecipientOrderByCreatedAtDesc(user)
                .stream()
                .filter(n -> !n.isRead())
                .toList();

        unread.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(unread);
    }
}