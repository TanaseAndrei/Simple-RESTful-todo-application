package com.nagarro.todoapp.infrastructure.repository;

import com.nagarro.todoapp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
