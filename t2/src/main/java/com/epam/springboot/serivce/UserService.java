package com.epam.springboot.serivce;

import com.epam.springboot.model.User;

import java.util.List;

public interface UserService {
    User findById(long id);

    List<User> findAll();

    User save(User user);

    void delete(long id);
}
