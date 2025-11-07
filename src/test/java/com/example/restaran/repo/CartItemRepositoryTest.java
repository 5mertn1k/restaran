package com.example.restaran.repo;

import com.example.restaran.model.CartItem;
import com.example.restaran.model.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private DishRepository dishRepo;

    @Test
    void saveAndFindBySessionId() {
        Dish dish = new Dish();
        dish.setTitle("Пицца");
        dish.setPrice(500);
        dishRepo.save(dish);


        CartItem item = new CartItem();
        item.setSessionId("abc");
        item.setDish(dish);
        item.setQuantity(2);
        cartRepo.save(item);


        List<CartItem> found = cartRepo.findBySessionId("abc");

        assertEquals(1, found.size());
        assertEquals("Пицца", found.get(0).getDish().getTitle());
    }

    @Test
    void deleteBySessionId_RemovesAll() {
        Dish dish = new Dish();
        dish.setTitle("Бургер");
        dish.setPrice(300);
        dishRepo.save(dish);

        CartItem item = new CartItem();
        item.setSessionId("xyz");
        item.setDish(dish);
        item.setQuantity(1);
        cartRepo.save(item);

        cartRepo.deleteBySessionId("xyz");

        assertTrue(cartRepo.findBySessionId("xyz").isEmpty());
    }
}
