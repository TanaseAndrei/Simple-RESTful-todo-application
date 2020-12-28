package com.nagarro.todoapp.infrastructure.gateway;

import com.nagarro.todoapp.domain.entity.User;
import com.nagarro.todoapp.infrastructure.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserJpaRepositoryGateway {

    private final UserJpaRepository userJpaRepository;

    public User save(User user) {
        return userJpaRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }
}
