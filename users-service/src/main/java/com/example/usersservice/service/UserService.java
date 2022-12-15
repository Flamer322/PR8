package com.example.usersservice.service;

import com.example.usersservice.repository.UserRepository;
import com.example.usersservice.client.NoteClient;
import com.example.usersservice.client.NotificationClient;
import com.example.usersservice.dto.EmailDTO;
import com.example.usersservice.dto.NoteDTO;
import com.example.usersservice.dto.NotificationDTO;
import com.example.usersservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private NoteClient noteClient;

    private NotificationClient notificationClient;

    public int getUserId(String userName) {
        return userRepository.findByName(userName).getId();
    }

    public User saveUser(String userName) {
        User user = userRepository.findByName(userName);

        if (user == null) {
            user = new User();

            user.setName(userName);

            userRepository.save(user);
        }

        return user;
    }

    public List<NoteDTO> getUserNotes(int userId) {
        return noteClient.getUserNotes(userId);
    }

    public void addUserNote(NoteDTO noteDTO) {
        noteClient.addUserNote(noteDTO);
    }

    public void deleteUserNote(int noteId) {
        noteClient.deleteUserNote(noteId);
    }

    public List<NotificationDTO> getUserNotifications(int userId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<NotificationDTO> notifications = notificationClient.getUserNotifications(userId);

        notifications.forEach(notification -> notification.setDate(
                LocalDateTime.parse(notification.getDate()).format(dateTimeFormatter))
        );

        return notifications;
    }

    public void addUserNotification(NotificationDTO notificationDTO) {
        notificationClient.addUserNotification(notificationDTO);
    }

    public String getUserEmail(int userId) {
        return notificationClient.getUserEmail(userId);
    }

    public void setUserEmail(EmailDTO emailDTO) {
        notificationClient.setUserEmail(emailDTO);
    }
}
