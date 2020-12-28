package com.nagarro.todoapp.application.endpoint.model.user;

import com.nagarro.todoapp.application.endpoint.model.task.TaskDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class UserDto {
    private String username;

    private String email;

    private String city;

    private String country;

    private LocalDateTime registrationDate;

    private List<TaskDto> taskList;
}
