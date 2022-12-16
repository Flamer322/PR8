package com.example.notificationsservice;

import com.example.notificationsservice.dto.EmailDTO;
import com.example.notificationsservice.dto.NotificationDTO;
import com.example.notificationsservice.model.Notification;
import com.example.notificationsservice.repository.NotificationRepository;
import com.example.notificationsservice.service.NotificationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NotificationsServiceApplicationTests {
    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    void addNotification() {
        NotificationDTO newNotificationDTO = new NotificationDTO();

        long count = notificationRepository.count();

        newNotificationDTO.setDate("2022-12-16 10:00");
        newNotificationDTO.setText("Тестовое уведомление");
        newNotificationDTO.setUserId(52);

        notificationService.addUserNotification(newNotificationDTO);

        Assertions.assertEquals(count + 1, notificationRepository.count());
    }

    @Test
    void getNotification() {
        List<Notification> notifications = notificationService.getUserNotifications(52);

        Assertions.assertEquals("notification test", notifications.get(0).getText());
    }

    @Test
    void getEmail(){
        String email = notificationService.getUserEmail(52);

        Assertions.assertEquals("brand@new.email", email);
    }

    @Test
    void setEmail(){
        EmailDTO newEmailDTO = new EmailDTO();

        newEmailDTO.setUserId(52);
        newEmailDTO.setEmail("brand@new.email");

        notificationService.setUserEmail(newEmailDTO);

        String email = notificationService.getUserEmail(52);

        Assertions.assertEquals("brand@new.email", email);
    }
}
