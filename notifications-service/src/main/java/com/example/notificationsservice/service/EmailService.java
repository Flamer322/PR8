package com.example.notificationsservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class EmailService {
    public JavaMailSender emailSender;
    private SpringTemplateEngine thymeleafTemplateEngine;

    public void sendEmailNotification(LocalDateTime dateTime, String email, String message) throws MessagingException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String date = dateTime.format(dateTimeFormatter);

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        Context thymeleafContext = new Context();

        thymeleafContext.setVariable("date", date);
        thymeleafContext.setVariable("message", message);

        String htmlBody = thymeleafTemplateEngine.process("notification.html", thymeleafContext);

        helper.setText(htmlBody, true);
        helper.setTo(email);
        helper.setSubject("Notification");
        helper.setFrom("test.notification@pr8.ru");

        emailSender.send(mimeMessage);
    }
}
