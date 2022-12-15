package com.example.notesservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="text", columnDefinition="TEXT")
    private String text;

    @Column(name="created_at")
    private String createdAt;
}
