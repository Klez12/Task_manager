package com.taskmanager.backend.controller;

import com.taskmanager.backend.entity.User;
import com.taskmanager.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Allow both ADMIN and USER (for your project demo)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}