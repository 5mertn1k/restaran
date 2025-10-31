package com.example.restaran.web;

import com.example.restaran.dto.CartItemDto;
import com.example.restaran.model.CartItem;
import com.example.restaran.model.Dish;
import com.example.restaran.repo.CartItemRepository;
import com.example.restaran.repo.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartItemRepository cartRepo;
    private final DishRepository dishRepo;

    // 📌 Получить корзину
    @GetMapping("/{sessionId}")
    public List<CartItemDto> getCart(@PathVariable String sessionId) {
        return cartRepo.findBySessionId(sessionId)
                .stream()
                .map(ci -> new CartItemDto(
                        ci.getId(),
                        ci.getSessionId(),
                        ci.getQuantity(),
                        ci.getDish().getId(),     // 👈 dishId
                        ci.getDish().getTitle(),
                        ci.getDish().getPrice()
                ))
                .toList();
    }


    // 📌 Установить количество блюда
    @PostMapping("/{sessionId}/set")
    public CartItemDto setQuantity(
            @PathVariable String sessionId,
            @RequestParam Long dishId,
            @RequestParam int quantity
    ) {
        Dish dish = dishRepo.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        CartItem existing = cartRepo.findBySessionIdAndDish(sessionId, dish);

        if (existing != null) {
            existing.setQuantity(quantity);
            cartRepo.save(existing);
            //           id        session   qty       dishId        title          price
            return new CartItemDto(existing.getId(), sessionId, quantity, dish.getId(), dish.getTitle(), dish.getPrice());
        }

        CartItem item = new CartItem();
        item.setSessionId(sessionId);
        item.setDish(dish);
        item.setQuantity(quantity);
        cartRepo.save(item);

        //           id        session   qty     dishId        title        price
        return new CartItemDto(item.getId(), sessionId, quantity, dish.getId(), dish.getTitle(), dish.getPrice());
    }

    // 📌 Удалить блюдо
    @DeleteMapping("/{sessionId}/remove/{dishId}")
    public void removeDish(@PathVariable String sessionId, @PathVariable Long dishId) {
        Dish dish = dishRepo.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        CartItem existing = cartRepo.findBySessionIdAndDish(sessionId, dish);
        if (existing != null) {
            cartRepo.delete(existing);
        }
    }

    // 📌 Очистить корзину
    @Transactional
    @DeleteMapping("/{sessionId}/clear")
    public void clearCart(@PathVariable String sessionId) {
        cartRepo.deleteBySessionId(sessionId);
    }
}
