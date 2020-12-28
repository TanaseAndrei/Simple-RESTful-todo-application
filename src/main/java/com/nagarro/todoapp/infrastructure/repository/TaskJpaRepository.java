package com.nagarro.todoapp.infrastructure.repository;

import com.nagarro.todoapp.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<Task, Long> {
}
