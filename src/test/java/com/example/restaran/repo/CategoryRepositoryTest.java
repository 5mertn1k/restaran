package com.example.restaran.repo;

import com.example.restaran.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(locations = "classpath:application-test.properties")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    @Rollback(false)
    void clearDatabase() {
        categoryRepository.deleteAll();
    }

    @Test
    void testFindBySlug() {
        Category category = new Category();
        category.setTitle("Тестовая категория по слагу");
        category.setSlug("slug-for-test");
        categoryRepository.save(category);


        Optional<Category> result = categoryRepository.findBySlug("slug-for-test");


        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Тестовая категория по слагу");
    }
}
