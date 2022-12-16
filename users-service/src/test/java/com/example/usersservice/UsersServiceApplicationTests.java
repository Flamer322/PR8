package com.example.usersservice;

import com.example.usersservice.repository.UserRepository;
import com.example.usersservice.service.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        long count = userRepository.count();

        userService.saveUser("new User");

        Assertions.assertEquals(count + 1, userRepository.count());
    }

    @Test
    void getUserEmail(){
        int userId = userService.getUserId("new User");

        String userEmail = userService.getUserEmail(userId);

        Assertions.assertEquals("testemail@test.ru", userEmail);
    }
}
