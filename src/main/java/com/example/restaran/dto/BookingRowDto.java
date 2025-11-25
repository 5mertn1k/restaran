package com.example.restaran.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record BookingRowDto(
        Long id,
        String lastName,
        String firstName,
        LocalDate date,
        LocalTime timeStart,
        LocalTime timeEnd,
        Integer guests,
        String phone
) {}