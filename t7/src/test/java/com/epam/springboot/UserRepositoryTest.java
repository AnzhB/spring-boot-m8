package com.epam.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import com.epam.springboot.model.User;
import com.epam.springboot.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:usersdata.sql")
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByIdTest() {
        User user = new User(1L, "user1", "user1@gmail.com");
        Assertions.assertEquals(user, userRepository.findById(1L).orElse(null));
    }

    @Test
    void findAllUsersTest() {
        User user1 = new User(1L, "user1", "user1@gmail.com");
        User user2 = new User(2L, "user2", "user2@gmail.com");
        User user3 = new User(3L, "user3", "user3@gmail.com");
        assertIterableEquals(List.of(user1, user2, user3), userRepository.findAll());
    }

    @Test
    void createUserTest() {
        User user = User.builder()
                .name("user4")
                .email("user4@gmail.com")
                .build();
        User save = userRepository.save(user);
        assertEquals(4, userRepository.findAll().size());
        Assertions.assertEquals(save, userRepository.findById(4L).orElse(null));
    }

    @Test
    void updateUserTest() {
        User user = new User(2L, "updatedUser", "updatedUser@gmail.com");
        userRepository.save(user);
        Assertions.assertEquals(user, userRepository.findById(2L).orElse(null));
    }
}
