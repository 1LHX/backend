package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(User user) {
        // 检查用户名是否已存在
        if (existsByUsername(user.getUsername())) {
            return "用户名已存在";
        }

        // 检查邮箱是否已存在
        if (existsByEmail(user.getEmail())) {
            return "邮箱已被注册";
        }

        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userMapper.register(user);
        return "注册成功";
    }

    @Override
    public String login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 生成JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("userId", user.getId());

            String token = jwtUtil.generateToken(claims);
            return token;
        }
        return null;
    }

    @Override
    public String updateUser(User user) {
        // 检查用户是否存在
        User existingUser = userMapper.getUserById(user.getId());
        if (existingUser == null) {
            return "用户不存在";
        }

        // 检查新用户名是否已被其他用户使用
        if (!existingUser.getUsername().equals(user.getUsername()) &&
                existsByUsername(user.getUsername())) {
            return "用户名已被其他用户使用";
        }

        // 检查新邮箱是否已被其他用户使用
        if (!existingUser.getEmail().equals(user.getEmail()) &&
                existsByEmail(user.getEmail())) {
            return "邮箱已被其他用户使用";
        }

        // 如果提供了密码，则加密新密码
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // 如果没有提供密码，保持原密码不变
            user.setPassword(existingUser.getPassword());
        }

        userMapper.updateUser(user);
        return "信息修改成功";
    }

    @Override
    public String updateUserByUsername(String username, User user) {
        // 检查原用户是否存在
        User existingUser = userMapper.getUserByUsername(username);
        if (existingUser == null) {
            return "用户不存在";
        }

        // 验证密码
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return "密码不正确";
        }

        // 检查新用户名是否已被其他用户使用
        if (!existingUser.getUsername().equals(user.getUsername()) &&
                existsByUsername(user.getUsername())) {
            return "用户名已被其他用户使用";
        }

        // 检查新邮箱是否已被其他用户使用
        if (!existingUser.getEmail().equals(user.getEmail()) &&
                existsByEmail(user.getEmail())) {
            return "邮箱已被其他用户使用";
        }

        // 设置用户ID
        user.setId(existingUser.getId());

        // 保持原密码不变（因为我们只是验证密码，不修改密码）
        user.setPassword(existingUser.getPassword());

        userMapper.updateUser(user);
        return "信息修改成功";
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    @Override
    public User getUserByIdWithPassword(Integer id) {
        User user = userMapper.getUserById(id);
        // 这里返回包含密码的完整用户信息
        return user;
    }

    @Override
    public User getUserByUsernameWithPassword(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 密码验证成功，返回用户信息
            return user;
        }
        // 密码验证失败或用户不存在
        return null;
    }
}