package com.example.restaran.web;

import com.example.restaran.dto.AdminUserDto;
import com.example.restaran.model.User;
import com.example.restaran.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminUserController {

    private final UserRepository userRepo;

    @GetMapping
    public List<AdminUserDto> getUsers() {
        return userRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private AdminUserDto toDto(User u) {
        String birth = u.getBirthDate() != null ? u.getBirthDate().toString() : "";
        String role = u.getRole() != null ? u.getRole().name() : "CUSTOMER";

        return new AdminUserDto(
                u.getId(),
                u.getLastName(),
                u.getFirstName(),
                birth,
                role,
                u.getUsername()
        );
    }
}
