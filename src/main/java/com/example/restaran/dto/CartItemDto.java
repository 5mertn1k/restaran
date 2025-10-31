package com.example.restaran.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDto {
    private Long id;
    private String sessionId;
    private int quantity;
    private Long dishId;   // 👈 для апдейта
    private String title;
    private int price;
}