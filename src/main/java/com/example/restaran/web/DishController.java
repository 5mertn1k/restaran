//package com.example.restaran.web;
//
//import com.example.restaran.model.Category;
//import com.example.restaran.model.Dish;
//import com.example.restaran.repo.CategoryRepository;
//import com.example.restaran.repo.DishRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/dishes")
//@RequiredArgsConstructor
//public class DishController {
//    private final DishRepository dishRepo;
//    private final CategoryRepository categoryRepo;
//
//    @GetMapping("/{slug}")
//    public List<Dish> getByCategory(@PathVariable String slug) {
//        Category category = categoryRepo.findBySlug(slug)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        return dishRepo.findByCategory(category);
//    }
//}

