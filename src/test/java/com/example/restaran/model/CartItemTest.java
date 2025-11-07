package com.example.restaran.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemTest {

    @Test
    void gettersAndSettersWork() {
        Dish dish = new Dish();
        dish.setTitle("Картошка фри");

        CartItem item = new CartItem();
        item.setDish(dish);
        item.setQuantity(3);
        item.setSessionId("session123");

        assertThat(item.getDish()).isSameAs(dish);
        assertThat(item.getQuantity()).isEqualTo(3);
        assertThat(item.getSessionId()).isEqualTo("session123");
    }
}
