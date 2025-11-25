package com.example.restaran.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    @Column(unique = true, nullable = false)
    private String username; // логин или телефон
    private String password;
}
