package com.nagarro.todoapp.infrastructure.gateway;

import com.nagarro.todoapp.domain.entity.Task;
import com.nagarro.todoapp.infrastructure.repository.TaskJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TaskJpaRepositoryGateway {

    private final TaskJpaRepository taskJpaRepository;

    public Task save(Task task) {
        return taskJpaRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id);
    }

    public void delete(Task task) {
        taskJpaRepository.delete(task);
    }

    public List<Task> getAll() {
        return taskJpaRepository.findAll();
    }
}
