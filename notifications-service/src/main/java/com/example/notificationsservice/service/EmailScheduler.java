package com.example.notificationsservice.service;

import com.example.notificationsservice.model.Notification;
import com.example.notificationsservice.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailScheduler {
    private final NotificationRepository notificationRepository;

    private final NotificationService notificationService;

    private EmailService emailService;

    @Scheduled(cron = "0 */5 * * * *")
    public void sendEmail() {
        final LocalDateTime now = LocalDateTime.now();

        List<Notification> notifications = notificationRepository.findOldNotifications(now);

        notifications.forEach(notification -> {
            try {
                emailService.sendEmailNotification(notification.getDate(), notificationService.getUserEmail(notification.getUserId()), notification.getText());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            notification.setSent(true);

            notificationRepository.save(notification);
        });
    }
}
