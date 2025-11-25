package com.example.restaran.dto;

public record BookingItemDto(
        Long dishId,
        String title,
        int price,
        int quantity
) {}

