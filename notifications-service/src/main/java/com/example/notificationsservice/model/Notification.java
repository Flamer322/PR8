package com.example.notificationsservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="text", columnDefinition="TEXT")
    private String text;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name="is_sent")
    private boolean isSent;
}
