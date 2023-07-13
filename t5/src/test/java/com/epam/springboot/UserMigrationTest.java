package com.epam.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.springboot.serivce.UserService;

@SpringBootTest
class UserMigrationTest {
    @Autowired
    private UserService userService;

    @Test
    void findAllUsersTest() {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    void findUserByIdTest() {
        assertEquals("user3@gmail.com", userService.findById(3L).getEmail());
    }
}
