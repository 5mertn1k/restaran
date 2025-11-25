package com.example.restaran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record BookingDetailsDto(
        Long id,
        String lastName,
        String firstName,
        LocalDate date,
        LocalTime timeStart,
        LocalTime timeEnd,
        int guests,
        String phone,
        int total,
        List<BookingItemDto> items
) {}
