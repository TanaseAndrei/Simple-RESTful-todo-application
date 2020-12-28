package com.nagarro.todoapp.application.service.converter;

import com.nagarro.todoapp.application.endpoint.model.task.TaskDto;
import com.nagarro.todoapp.domain.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {

    public List<TaskDto> convertToDtoList(List<Task> taskList) {
        return taskList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTask(task.getTask());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    public Task convertToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTask(taskDto.getTask());
        task.setStatus(taskDto.getStatus());
        return task;
    }
}
