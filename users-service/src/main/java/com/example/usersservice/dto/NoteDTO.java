package com.example.usersservice.dto;

import lombok.Data;

@Data
public class NoteDTO {
    private int id;
    private int userId;
    private String text;
    private String createdAt;
}
