package com.example.restaran.dto;

public record BookingCreateDto(
        String lastName,
        String firstName,
        String guests,      // придёт строкой – преобразуем
        String date,        // "2025-05-12"
        String time,        // "12:30"
        String duration,    // "2" (часы, условно)
        String phone
) {}
