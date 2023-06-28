package com.epam.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.epam.springboot.model.User;
import com.epam.springboot.repository.UserRepository;
import com.epam.springboot.serivce.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findByIdTest() {
        User expectedUser = new User(1L, "test", "test@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.findById(1L);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByIdNoSuchElementExceptionTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
    }

    @Test
    void findAllUsersTest() {
        List<User> expectedUser = List.of(new User(1L, "test", "test@gmail.com"));
        when(userRepository.findAll()).thenReturn(expectedUser);
        List<User> actual = userService.findAll();
        assertIterableEquals(expectedUser, actual);
    }

    @Test
    void saveTest() {
        User expectedUser = new User(1L, "test", "test@gmail.com");
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);
        User actual = userService.save(expectedUser);
        assertEquals(expectedUser, actual);
    }
}
