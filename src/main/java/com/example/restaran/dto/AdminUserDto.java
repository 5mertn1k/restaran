package com.example.restaran.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public record AdminUserDto(
        Long id,
        String lastName,
        String firstName,
        String birthDate,
        String role,
        String username
) {}