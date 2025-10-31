package com.example.restaran.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cart_item",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_cart_session_dish",
                columnNames = {"session_id", "dish_id"}))
public class  CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dish dish;

    private int quantity;
}
