package com.example.notificationsservice.controller;

import com.example.notificationsservice.service.NotificationService;
import com.example.notificationsservice.dto.EmailDTO;
import com.example.notificationsservice.dto.NotificationDTO;
import com.example.notificationsservice.model.Notification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getUserNotions(@PathVariable("userId") int userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUserNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.addUserNotification(notificationDTO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{userId}")
    String getUserEmail(@PathVariable("userId") int userId) {
        return notificationService.getUserEmail(userId);
    }

    @PostMapping("/email")
    public ResponseEntity<Object> setUserEmail(@RequestBody EmailDTO emailDTO) {
        notificationService.setUserEmail(emailDTO);

        return ResponseEntity.noContent().build();
    }
}
