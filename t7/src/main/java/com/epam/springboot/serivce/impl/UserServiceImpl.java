package com.epam.springboot.serivce.impl;

import java.util.List;
import java.util.NoSuchElementException;

import com.epam.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.epam.springboot.model.User;
import com.epam.springboot.serivce.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(long id) {
        log.info("find by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public List<User> findAll() {
        log.info("find all");
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        log.info("save: {}", user);
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        log.info("delete: {}", id);
        userRepository.deleteById(id);
    }
}
