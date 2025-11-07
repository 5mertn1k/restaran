package com.example.restaran.repo;

import com.example.restaran.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("test_user_123");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> result = userRepository.findByUsername("test_user_123");

        assertThat(result).isPresent();
        assertThat(result.get().getUsername()).isEqualTo("test_user_123");

    }
}
