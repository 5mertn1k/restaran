package com.example.restaran.repo;

import com.example.restaran.model.CartItem;
import com.example.restaran.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findBySessionId(String sessionId);

    CartItem findBySessionIdAndDish(String sessionId, Dish dish);

    void deleteBySessionId(String sessionId);

    void deleteBySessionIdAndDish(String sessionId, Dish dish);
}
