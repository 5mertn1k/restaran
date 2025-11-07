package com.example.restaran.web;

import com.example.restaran.model.User;
import com.example.restaran.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_SavesUser() throws Exception {
        User input = new User();
        input.setUsername("ivan");
        input.setPassword("1234");

        User saved = new User();
        saved.setId(1L);
        saved.setUsername("ivan");
        saved.setPassword("1234");

        when(userRepo.save(any(User.class))).thenReturn(saved);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("ivan"));
    }

    @Test
    void all_ReturnsAllUsers() throws Exception {
        User u1 = new User();
        u1.setId(1L);
        u1.setUsername("one");

        User u2 = new User();
        u2.setId(2L);
        u2.setUsername("two");

        when(userRepo.findAll()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value("one"))
                .andExpect(jsonPath("$[1].username").value("two"));
    }
}
