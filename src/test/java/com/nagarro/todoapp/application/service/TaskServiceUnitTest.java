package com.nagarro.todoapp.application.service;

import com.nagarro.todoapp.application.endpoint.model.task.TaskDto;
import com.nagarro.todoapp.application.service.converter.TaskConverter;
import com.nagarro.todoapp.domain.entity.Task;
import com.nagarro.todoapp.domain.entity.User;
import com.nagarro.todoapp.infrastructure.gateway.TaskJpaRepositoryGateway;
import com.nagarro.todoapp.infrastructure.gateway.UserJpaRepositoryGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit test for TaskService")
class TaskServiceUnitTest {

    @Mock
    private TaskJpaRepositoryGateway taskJpaRepositoryGateway;

    @Mock
    private UserJpaRepositoryGateway userJpaRepositoryGateway;

    @Mock
    private TaskConverter taskConverter;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Test if the task's id is returned")
    void testAddTask() {
        User user = getUser();
        Task task = getTask();
        task.setUser(user);
        user.getTaskList().add(task);
        TaskDto taskDto = getTaskDto(task);

        when(userJpaRepositoryGateway.findById(user.getId())).thenReturn(Optional.of(user));
        when(taskConverter.convertToTask(taskDto)).thenReturn(task);
        when(userJpaRepositoryGateway.save(user)).thenReturn(user);
        when(taskJpaRepositoryGateway.save(task)).thenReturn(task);

        assertThat(taskService.add(user.getId(), taskDto)).isEqualTo(task.getId());
    }

    @Test
    @DisplayName("Test if the task's list is returned")
    void testGetAllTasks() {
        User user = getUser();
        Task task = getTask();
        task.setUser(user);
        user.getTaskList().add(task);
        TaskDto taskDto = getTaskDto(task);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(userJpaRepositoryGateway.findById(user.getId())).thenReturn(Optional.of(user));
        when(taskConverter.convertToDtoList(user.getTaskList())).thenReturn(taskDtoList);

        assertThat(taskService.getAll(user.getId())).isEqualTo(taskDtoList);
    }

    @Test
    @DisplayName("Test if the task is set to complete")
    void testCompleteTask() {
        User user = getUser();
        Task task = getTask();
        task.setUser(user);
        user.getTaskList().add(task);
        TaskDto taskDto = getTaskDto(task);

        when(userJpaRepositoryGateway.findById(user.getId())).thenReturn(Optional.of(user));
        when(taskJpaRepositoryGateway.save(task)).thenReturn(task);
        when(taskConverter.convertToDto(task)).thenReturn(taskDto);

        assertThat(taskService.complete(user.getId(), task.getId())).isEqualTo(taskDto);
    }

    @Test
    @DisplayName("Test if the delete method is called")
    void testDeleteTask() {
        User user = getUser();
        Task task = getTask();
        task.setUser(user);
        user.getTaskList().add(task);
        TaskDto taskDto = getTaskDto(task);

        when(userJpaRepositoryGateway.findById(user.getId())).thenReturn(Optional.of(user));

        taskService.delete(user.getId(), task.getId());

        verify(taskJpaRepositoryGateway, times(1)).delete(task);
    }

    private Task getTask() {
        Task task = new Task();
        task.setTask("bread");
        task.setStatus("In progress");
        task.setId(1L);
        return task;
    }

    private TaskDto getTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTask(task.getTask());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("aa@yahoo.com");
        user.setCountry("Romania");
        user.setCity("Craiova");
        user.setRegistrationDate(LocalDateTime.now());
        user.setTaskList(new ArrayList<>());
        user.setUsername("Andrei");
        return user;
    }
}