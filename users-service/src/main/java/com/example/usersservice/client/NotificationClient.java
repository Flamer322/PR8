package com.example.usersservice.client;

import com.example.usersservice.dto.EmailDTO;
import com.example.usersservice.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "notifications-service")
public interface NotificationClient {
    @GetMapping("/{userId}")
    List<NotificationDTO> getUserNotifications(@PathVariable("userId") int userId);

    @PostMapping("/")
    ResponseEntity<Object> addUserNotification(@RequestBody NotificationDTO notificationDTO);

    @GetMapping("/email/{userId}")
    String getUserEmail(@PathVariable("userId") int userId);

    @PostMapping("/email")
    String setUserEmail(@RequestBody EmailDTO emailDTO);
}
