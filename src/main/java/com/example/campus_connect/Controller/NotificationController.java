package com.example.campus_connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.campus_connect.Entity.NotificationEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.UsersRepository;
import com.example.campus_connect.Service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UsersRepository usersRepository; // to fetch authenticated user

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationEntity>> getNotifications(@PathVariable String userId) {
        UsersEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(notificationService.getUserNotifications(user));
    }

    @PutMapping("/mark-all-read/{userId}")
    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {
        UsersEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        notificationService.markAllAsRead(user);
        return ResponseEntity.ok().build();
    }
}
