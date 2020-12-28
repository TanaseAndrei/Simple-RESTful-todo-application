package com.nagarro.todoapp.infrastructure.repository;

import com.nagarro.todoapp.domain.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("TaskJpaRepository integration tests")
class TaskJpaRepositoryIntegrationTest {

    @Autowired
    private TaskJpaRepository taskJpaRepository;

    @Test
    @DisplayName("Test if the TaskJpaRepository is injected")
    void testContextIsLoaded() {
        assertThat(taskJpaRepository).isNotNull();
    }

    @Test
    @DisplayName("If the task is saved, the list should not be empty")
    void testAddTask() {
        Task task = createTask();

        Task createdTask = taskJpaRepository.save(task);

        assertThat(taskJpaRepository.findAll()).isNotEmpty();
        assertThat(createdTask).isEqualTo(task);
    }

    @Test
    @DisplayName("If the searched task does not exist, the optional should be empty")
    void testUnexistentTaskFindById() {
        Optional<Task> optionalTask = taskJpaRepository.findById(1000L);

        assertThat(optionalTask).isNotPresent();
    }

    @Test
    @DisplayName("If the task is deleted, the list of tasks should be empty")
    void testDeleteTask() {
        Task task = createTask();
        Task createdTask = taskJpaRepository.save(task);

        taskJpaRepository.delete(createdTask);

        assertThat(taskJpaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("Test if the task's updates are made with success")
    void testUpdateTask() {
        Task task = createTask();
        Task createdTask = taskJpaRepository.save(task);

        createdTask.setTask("UpdatedTask");
        createdTask.setStatus("Done");
        Task updatedTask = taskJpaRepository.save(createdTask);

        assertThat(updatedTask.getTask()).isEqualTo("UpdatedTask");
        assertThat(updatedTask.getStatus()).isEqualTo("Done");
    }

    private Task createTask() {
        Task task = new Task();
        task.setTask("Going to Hawaii");
        task.setStatus("In progress");
        task.setUser(null);
        return task;
    }
}
