package com.example.restaran.web;

import com.example.restaran.model.User;
import com.example.restaran.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;

    // 📌 Регистрация
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }
        return userRepo.save(user);
    }

    // 📌 Вход
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        return userRepo.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Неверный логин или пароль"));
    }
}
