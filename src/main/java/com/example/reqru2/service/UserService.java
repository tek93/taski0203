package com.example.reqru2.service;

import com.example.reqru2.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);



//    Long findUserByName(String name);

    Long getCurrentlyUserId();

    User findByUsername(String username);

    List<User> findAllUsers();

    User updateUser(User user);
}
