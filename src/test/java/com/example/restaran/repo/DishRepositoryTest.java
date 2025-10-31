package com.example.restaran.repo;

import com.example.restaran.model.Category;
import com.example.restaran.model.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.mode=never")
class DishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testFindByCategory() {
        // создаём категорию без ID
        Category category = new Category();
        category.setTitle("Тестовая категория");
        category.setSlug("test-category");
        category = categoryRepository.save(category); // Hibernate сам присвоит id

        // создаём блюдо
        Dish dish = new Dish();
        dish.setTitle("Тестовое блюдо");
        dish.setPrice(500);
        dish.setCategory(category);

        dishRepository.save(dish);

        // ищем блюда по категории
        List<Dish> result = dishRepository.findByCategory(category);

        // проверяем результат
        assertThat(result)
                .isNotEmpty()
                .anyMatch(d -> d.getTitle().equals("Тестовое блюдо"));
    }
}
