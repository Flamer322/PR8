package com.example.usersservice.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private int id;
    private int userId;
    private String text;
    private String date;
}
