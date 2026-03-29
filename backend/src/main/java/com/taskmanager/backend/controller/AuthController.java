package com.taskmanager.backend.controller;

import com.taskmanager.backend.entity.User;
import com.taskmanager.backend.repository.UserRepository;
import com.taskmanager.backend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
public User register(@RequestBody User user) {

    System.out.println("User received: " + user);

    user.setPassword(encoder.encode(user.getPassword()));
    user.setRole("USER");

    return userRepository.save(user);
}

    @PostMapping("/login")
public Map<String, String> login(@RequestBody User user) {

    User dbUser = userRepository.findByEmail(user.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtUtil.generateToken(user.getEmail());

    return Map.of("token", token);
}
}