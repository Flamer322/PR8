package com.example.notificationsservice.service;

import com.example.notificationsservice.dto.EmailDTO;
import com.example.notificationsservice.dto.NotificationDTO;
import com.example.notificationsservice.model.Email;
import com.example.notificationsservice.model.Notification;
import com.example.notificationsservice.repository.EmailRepository;
import com.example.notificationsservice.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    private EmailRepository emailRepository;

    public List<Notification> getUserNotifications(int userId){
        return notificationRepository.findAllByUserId(userId);
    }

    public void addUserNotification(NotificationDTO notificationDTO){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Notification notification = new Notification();

        notification.setUserId(notificationDTO.getUserId());
        notification.setText(notificationDTO.getText());
        notification.setDate(LocalDateTime.parse(notificationDTO.getDate(), dateTimeFormatter));
        notification.setSent(false);

        notificationRepository.save(notification);
    }

    public String getUserEmail(int userId){
        Email email = emailRepository.findByUserId(userId);

        if (email != null) {
            return email.getEmail();
        } else {
            return "";
        }
    }

    public void setUserEmail(EmailDTO emailDTO){
        Email email = emailRepository.findByUserId(emailDTO.getUserId());

        if (email == null) {
            email = new Email();

            email.setUserId(emailDTO.getUserId());
        }

        email.setEmail(emailDTO.getEmail());

        emailRepository.save(email);
    }
}
