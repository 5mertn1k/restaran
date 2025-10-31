package com.example.restaran.web;

import com.example.restaran.model.Category;
import com.example.restaran.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repo;

    @GetMapping
    public List<Category> getAll() {
        return repo.findAll();
    }
}
