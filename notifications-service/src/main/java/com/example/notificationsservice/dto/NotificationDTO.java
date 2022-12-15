package com.example.notificationsservice.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private int userId;
    private String text;
    private String date;
}
