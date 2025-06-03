package com.example.service;

import com.example.pojo.User;

public interface UserService {
    String register(User user);

    String login(String username, String password);

    String updateUser(User user);

    String updateUserByUsername(String username, User user);

    User getUserById(Integer id);

    User getUserByIdWithPassword(Integer id);

    User getUserByUsername(String username);

    User getUserByUsernameWithPassword(String username, String password);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}