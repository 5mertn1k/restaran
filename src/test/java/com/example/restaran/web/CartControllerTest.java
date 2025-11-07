package com.example.restaran.web;

import com.example.restaran.model.CartItem;
import com.example.restaran.model.Dish;
import com.example.restaran.repo.CartItemRepository;
import com.example.restaran.repo.DishRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartItemRepository cartRepo;

    @MockBean
    private DishRepository dishRepo;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getCart_ReturnsCartItems() throws Exception {
        Dish dish = new Dish();
        dish.setId(10L);
        dish.setTitle("Пицца");
        dish.setPrice(500);

        CartItem ci = new CartItem();
        ci.setId(1L);
        ci.setSessionId("abc");
        ci.setQuantity(2);
        ci.setDish(dish);

        when(cartRepo.findBySessionId("abc")).thenReturn(List.of(ci));

        mockMvc.perform(get("/api/cart/abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].sessionId").value("abc"))
                .andExpect(jsonPath("$[0].dishId").value(10L))
                .andExpect(jsonPath("$[0].title").value("Пицца"))
                .andExpect(jsonPath("$[0].price").value(500))
                .andExpect(jsonPath("$[0].quantity").value(2));
    }


    @Test
    void setQuantity_NewItem_CreatesCartItem() throws Exception {
        Dish dish = new Dish();
        dish.setId(10L);
        dish.setTitle("Пицца");
        dish.setPrice(500);

        when(dishRepo.findById(10L)).thenReturn(Optional.of(dish));
        when(cartRepo.findBySessionIdAndDish("abc", dish)).thenReturn(null);

        when(cartRepo.save(any(CartItem.class))).thenAnswer(invocation -> {
            CartItem item = invocation.getArgument(0);
            item.setId(1L);
            return item;
        });

        mockMvc.perform(post("/api/cart/abc/set")
                        .param("dishId", "10")
                        .param("quantity", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.sessionId").value("abc"))
                .andExpect(jsonPath("$.dishId").value(10L))
                .andExpect(jsonPath("$.quantity").value(3));
    }


    @Test
    void setQuantity_ExistingItem_UpdatesQuantity() throws Exception {
        Dish dish = new Dish();
        dish.setId(10L);
        dish.setTitle("Пицца");
        dish.setPrice(500);

        when(dishRepo.findById(10L)).thenReturn(Optional.of(dish));

        CartItem existing = new CartItem();
        existing.setId(1L);
        existing.setSessionId("abc");
        existing.setDish(dish);
        existing.setQuantity(1);

        when(cartRepo.findBySessionIdAndDish("abc", dish)).thenReturn(existing);
        when(cartRepo.save(any(CartItem.class))).thenReturn(existing);

        mockMvc.perform(post("/api/cart/abc/set")
                        .param("dishId", "10")
                        .param("quantity", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(5));
    }


    @Test
    void removeDish_ExistingItem_DeletesIt() throws Exception {
        Dish dish = new Dish();
        dish.setId(10L);

        CartItem existing = new CartItem();
        existing.setId(1L);
        existing.setSessionId("abc");
        existing.setDish(dish);
        existing.setQuantity(1);

        when(dishRepo.findById(10L)).thenReturn(Optional.of(dish));
        when(cartRepo.findBySessionIdAndDish("abc", dish)).thenReturn(existing);

        mockMvc.perform(delete("/api/cart/abc/remove/10"))
                .andExpect(status().isOk());

        verify(cartRepo).delete(existing);
    }


    @Test
    void clearCart_DeletesAllBySession() throws Exception {
        mockMvc.perform(delete("/api/cart/abc/clear"))
                .andExpect(status().isOk());

        verify(cartRepo).deleteBySessionId("abc");
    }
}
