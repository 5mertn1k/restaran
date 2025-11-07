package com.example.restaran.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DishTest {

    @Test
    void gettersAndSettersWork() {
        Category category = new Category();
        category.setTitle("Пицца");
        category.setSlug("pizza");

        Dish dish = new Dish();
        dish.setTitle("Маргарита");
        dish.setPrice(500);
        dish.setKcal(800);
        dish.setProteins(20);
        dish.setFats(10);
        dish.setCarbs(90);
        dish.setCategory(category);

        assertThat(dish.getTitle()).isEqualTo("Маргарита");
        assertThat(dish.getPrice()).isEqualTo(500);
        assertThat(dish.getKcal()).isEqualTo(800);
        assertThat(dish.getProteins()).isEqualTo(20);
        assertThat(dish.getFats()).isEqualTo(10);
        assertThat(dish.getCarbs()).isEqualTo(90);
        assertThat(dish.getCategory()).isSameAs(category);
    }
}
