package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void register(User user);

    User login(String username);

    void updateUser(User user);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}