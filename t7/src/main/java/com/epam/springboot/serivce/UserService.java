package com.epam.springboot.serivce;

import java.util.List;

import com.epam.springboot.model.User;

public interface UserService {
    User findById(long id);

    List<User> findAll();

    User save(User user);

    void delete(long id);
}
