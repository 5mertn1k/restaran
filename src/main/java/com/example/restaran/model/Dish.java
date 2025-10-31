package com.example.restaran.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int price;
    private int kcal;
    private int proteins;
    private int fats;
    private int carbs;

    @ManyToOne
    private Category category;
}
