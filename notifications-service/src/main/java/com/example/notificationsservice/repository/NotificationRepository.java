package com.example.notificationsservice.repository;

import com.example.notificationsservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByUserId(int userId);

    @Query(value = "SELECT n FROM Notification n WHERE n.date <= :date AND n.isSent = false ")
    List<Notification> findOldNotifications(LocalDateTime date);
}
