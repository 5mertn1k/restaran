package com.example.restaran.web;

import com.example.restaran.model.Category;
import com.example.restaran.model.Dish;
import com.example.restaran.repo.CategoryRepository;
import com.example.restaran.repo.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishRepository dishRepo;
    private final CategoryRepository categoryRepo;

    @GetMapping("/{slug}")
    public List<Dish> getByCategory(@PathVariable String slug) {
        Category cat = categoryRepo.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Категория не найдена: " + slug));
        return dishRepo.findByCategory(cat);
    }
}
