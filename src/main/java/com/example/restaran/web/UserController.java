package com.example.restaran.web;

import com.example.restaran.model.User;
import com.example.restaran.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }
}
