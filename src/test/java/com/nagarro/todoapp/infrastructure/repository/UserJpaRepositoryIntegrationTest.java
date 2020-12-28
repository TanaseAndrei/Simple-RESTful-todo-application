package com.nagarro.todoapp.infrastructure.repository;

import com.nagarro.todoapp.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("UserJpaRepository integration tests")
class UserJpaRepositoryIntegrationTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("Test if the UserJpaRepository is injected")
    void testContextIsLoaded() {
        assertThat(userJpaRepository).isNotNull();
    }

    @Test
    @DisplayName("If the user is saved, the list should not be empty")
    void testAddUser() {
        User user = createUser();

        User createdUser = userJpaRepository.save(user);

        assertThat(userJpaRepository.findAll()).isNotEmpty();
        assertThat(createdUser).isEqualTo(user);
    }

    @Test
    @DisplayName("If the searched user does not exist, the optional should be empty")
    void testUnexistentUserFindById() {
        Optional<User> optionalUser = userJpaRepository.findById(1000L);

        assertThat(optionalUser).isNotPresent();
    }

    @Test
    @DisplayName("If the user is deleted, the list of users should be empty")
    void testDeleteUser() {
        User user = createUser();
        User savedUser = userJpaRepository.save(user);

        userJpaRepository.delete(savedUser);

        assertThat(userJpaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Test if the user's updates are made with success")
    void testUpdateUser() {
        User user = createUser();
        User savedUser = userJpaRepository.save(user);

        savedUser.setUsername("UpdatedName");
        User updatedUser = userJpaRepository.save(savedUser);

        assertThat(updatedUser.getUsername()).isEqualTo("UpdatedName");
    }

    private User createUser() {
        User user = new User();
        user.setUsername("Andrei");
        user.setRegistrationDate(LocalDateTime.now());
        user.setEmail("ceva@yahoo.com");
        user.setCity("Craiova");
        user.setCountry("Romania");
        user.setTaskList(new ArrayList<>());
        return user;
    }
}
