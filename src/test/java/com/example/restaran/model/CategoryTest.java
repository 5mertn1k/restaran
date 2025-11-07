package com.example.restaran.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    void gettersAndSettersWork() {
        Category category = new Category();
        category.setTitle("Закуски");
        category.setSlug("snacks");

        assertThat(category.getTitle()).isEqualTo("Закуски");
        assertThat(category.getSlug()).isEqualTo("snacks");
    }
}
