package com.nagarro.todoapp.application.service;

import com.nagarro.todoapp.application.endpoint.model.task.TaskDto;
import com.nagarro.todoapp.application.exception.AppException;
import com.nagarro.todoapp.application.service.converter.TaskConverter;
import com.nagarro.todoapp.domain.entity.Task;
import com.nagarro.todoapp.domain.entity.User;
import com.nagarro.todoapp.infrastructure.gateway.TaskJpaRepositoryGateway;
import com.nagarro.todoapp.infrastructure.gateway.UserJpaRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private static final String USER_NOT_FOUND_MESSAGE = "The user has not been found";
    private static final String TASK_NOT_FOUND_MESSAGE = "The task has not been found";

    private final TaskJpaRepositoryGateway taskJpaRepositoryGateway;
    private final UserJpaRepositoryGateway userJpaRepositoryGateway;
    private final TaskConverter taskConverter;

    public Long add(Long userId, TaskDto taskDto) {
        User user = getUser(userId);
        Task task = taskConverter.convertToTask(taskDto);
        user.getTaskList().add(task);
        task.setUser(user);
        userJpaRepositoryGateway.save(user);
        taskJpaRepositoryGateway.save(task);
        return task.getId();
    }

    private User getUser(Long userId) {
        return userJpaRepositoryGateway.findById(userId).orElseThrow(() -> new AppException(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
    }

    public List<TaskDto> getAll(Long userId) {
        User user = getUser(userId);
        return taskConverter.convertToDtoList(user.getTaskList());
    }

    public TaskDto complete(Long userId, Long taskId) {
        User user = getUser(userId);
        Task usersTask = getUsersTask(taskId, user);
        usersTask.setStatus("Done");
        taskJpaRepositoryGateway.save(usersTask);
        return taskConverter.convertToDto(usersTask);
    }

    private Task getUsersTask(Long taskId, User user) {
        return user.getTaskList()
                .stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new AppException(TASK_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
    }

    public void delete(Long userId, Long taskId) {
        User user = getUser(userId);
        Task usersTask = getUsersTask(taskId, user);
        taskJpaRepositoryGateway.delete(usersTask);
    }
}
