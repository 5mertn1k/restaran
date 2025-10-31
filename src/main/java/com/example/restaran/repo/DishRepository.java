package com.example.restaran.repo;

import com.example.restaran.model.Category;
import com.example.restaran.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCategory(Category category);

}

