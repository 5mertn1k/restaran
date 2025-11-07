package com.example.restaran.web;

import com.example.restaran.model.Category;
import com.example.restaran.repo.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepo;

    @Test
    void getAll_ReturnsCategories() throws Exception {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setSlug("zakuski");
        c1.setTitle("Закуски");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setSlug("salads");
        c2.setTitle("Салаты");

        when(categoryRepo.findAll()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].slug").value("zakuski"))
                .andExpect(jsonPath("$[1].id").value(2L));
    }
}
