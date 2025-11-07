package com.example.restaran.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void gettersAndSettersWork() {
        User user = new User();
        user.setUsername("test_user");
        user.setPassword("secret");
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setMiddleName("Иванович");
        user.setBirthDate("2000-01-01");

        assertThat(user.getUsername()).isEqualTo("test_user");
        assertThat(user.getPassword()).isEqualTo("secret");
        assertThat(user.getFirstName()).isEqualTo("Иван");
        assertThat(user.getLastName()).isEqualTo("Иванов");
        assertThat(user.getMiddleName()).isEqualTo("Иванович");
        assertThat(user.getBirthDate()).isEqualTo("2000-01-01");
    }
}
