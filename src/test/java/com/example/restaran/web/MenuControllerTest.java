package com.example.restaran.web;

import com.example.restaran.model.Category;
import com.example.restaran.model.Dish;
import com.example.restaran.repo.CategoryRepository;
import com.example.restaran.repo.DishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuController.class)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DishRepository dishRepository;

    @MockBean
    private CategoryRepository categoryRepository; // 👈 мок категории

    @Test
    void testGetMenuBySlug() throws Exception {

        Category category = new Category();
        category.setSlug("pizza");


        Dish dish = new Dish();
        dish.setTitle("Пицца Маргарита");
        dish.setCategory(category);


        when(categoryRepository.findBySlug("pizza"))
                .thenReturn(Optional.of(category));


        when(dishRepository.findByCategory(category))
                .thenReturn(List.of(dish));


        mockMvc.perform(get("/api/menu/pizza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Пицца Маргарита"));
    }
}

