package com.example.notificationsservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="email")
    private String email;
}
