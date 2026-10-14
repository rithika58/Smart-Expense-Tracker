package com.project.expense_tracker.controller;

import com.project.expense_tracker.dto.UserDTO;
import com.project.expense_tracker.entity.User;
import com.project.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.expense_tracker.dto.LoginDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserDTO userDTO) {

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        return userService.loginUser(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );
    }
}