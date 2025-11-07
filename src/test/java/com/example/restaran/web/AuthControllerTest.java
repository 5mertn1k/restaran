package com.example.restaran.web;

import com.example.restaran.model.User;
import com.example.restaran.repo.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    // создаём "фейковый" репозиторий (Mockito его подменяет)
    private final UserRepository userRepo = mock(UserRepository.class);

    // создаём настоящий контроллер, но передаём ему фейковый репозиторий
    private final AuthController controller = new AuthController(userRepo);



    @Test
    void register_NewUser_ReturnsSavedUser() {

        User input = new User();
        input.setUsername("ivan");
        input.setPassword("1234");


        User saved = new User();
        saved.setId(1L);
        saved.setUsername("ivan");
        saved.setPassword("1234");


        when(userRepo.findByUsername("ivan")).thenReturn(Optional.empty());
        when(userRepo.save(input)).thenReturn(saved);


        User result = controller.register(input);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("ivan", result.getUsername());
        verify(userRepo).save(input);
    }



    @Test
    void register_ExistingUser_ThrowsError() {

        User existing = new User();
        existing.setId(1L);
        existing.setUsername("ivan");
        existing.setPassword("1234");


        when(userRepo.findByUsername("ivan")).thenReturn(Optional.of(existing));


        User input = new User();
        input.setUsername("ivan");
        input.setPassword("abcd");


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.register(input);
        });

        assertEquals("Пользователь уже существует", exception.getMessage());
        verify(userRepo, never()).save(any());
    }



    @Test
    void login_ValidCredentials_ReturnsUser() {

        User user = new User();
        user.setId(1L);
        user.setUsername("ivan");
        user.setPassword("1234");


        when(userRepo.findByUsername("ivan")).thenReturn(Optional.of(user));


        Map<String, String> request = Map.of(
                "username", "ivan",
                "password", "1234"
        );


        User result = controller.login(request);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("ivan", result.getUsername());
    }



    @Test
    void login_WrongPassword_ReturnsError() {

        User user = new User();
        user.setId(1L);
        user.setUsername("ivan");
        user.setPassword("1234");

        when(userRepo.findByUsername("ivan")).thenReturn(Optional.of(user));


        Map<String, String> request = Map.of(
                "username", "ivan",
                "password", "wrong"
        );


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.login(request);
        });

        assertEquals("Неверный логин или пароль", exception.getMessage());
    }
}
