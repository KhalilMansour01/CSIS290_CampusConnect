package com.example.campus_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.campus_connect.Entity.NotificationEntity;
import com.example.campus_connect.Entity.UsersEntity;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByRecipientOrderByCreatedAtDesc(UsersEntity recipient);
    long countByRecipientAndIsReadFalse(UsersEntity recipient);
}