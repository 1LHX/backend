package com.example.controller;

import com.example.common.Result;
import com.example.dto.LoginRequest;
import com.example.pojo.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * 
     * @param user          用户信息
     * @param bindingResult 验证结果
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody User user, BindingResult bindingResult) {
        // 参数验证
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().isEmpty()
                    ? "参数验证失败"
                    : bindingResult.getAllErrors().get(0).getDefaultMessage();
            return Result.error(errorMsg);
        }

        String result = userService.register(user);
        if ("注册成功".equals(result)) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }

    /**
     * 用户登录（支持JSON请求体）
     * 
     * @param loginRequest  登录请求
     * @param bindingResult 验证结果
     * @return 登录结果（包含JWT token）
     */
    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        // 参数验证
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().isEmpty()
                    ? "参数验证失败"
                    : bindingResult.getAllErrors().get(0).getDefaultMessage();
            return Result.error(errorMsg);
        }

        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (token != null) {
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 用户登录（兼容表单参数）
     * 
     * @param username 用户名
     * @param password 密码
     * @return 登录结果（包含JWT token）
     */
    @PostMapping("/login-form")
    public Result loginForm(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        String token = userService.login(username, password);
        if (token != null) {
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 更新用户信息
     * 
     * @param username 用户名（路径参数）
     * @param user     用户信息（包含密码）
     * @return 更新结果
     */
    @PostMapping("/update/{username}")
    public Result update(@PathVariable String username, @RequestBody User user) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return Result.error("新用户名不能为空");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return Result.error("邮箱不能为空");
        }

        String result = userService.updateUserByUsername(username, user);
        if ("信息修改成功".equals(result)) {
            return Result.success(result);
        } else {
            return Result.error(result);
        }
    }

    /**
     * 根据ID获取用户信息（包含密码）
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return Result.error("用户ID无效");
        }

        User user = userService.getUserByIdWithPassword(id);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }

    /**
     * 根据用户名获取用户信息（需要密码验证）
     * 
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @GetMapping("/username/{username}")
    public Result getUserByUsername(@PathVariable String username, @RequestParam String password) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        if (password == null || password.trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        User user = userService.getUserByUsernameWithPassword(username, password);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 检查用户名是否存在
     * 
     * @param username 用户名
     * @return 检查结果
     */
    @GetMapping("/check-username")
    public Result checkUsername(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }

        boolean exists = userService.existsByUsername(username);
        return Result.success(exists);
    }

    /**
     * 检查邮箱是否存在
     * 
     * @param email 邮箱
     * @return 检查结果
     */
    @GetMapping("/check-email")
    public Result checkEmail(@RequestParam String email) {
        if (email == null || email.trim().isEmpty()) {
            return Result.error("邮箱不能为空");
        }

        boolean exists = userService.existsByEmail(email);
        return Result.success(exists);
    }
}