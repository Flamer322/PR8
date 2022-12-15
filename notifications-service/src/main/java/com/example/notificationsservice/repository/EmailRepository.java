package com.example.notificationsservice.repository;

import com.example.notificationsservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    Email findByUserId(int userId);
}
