package com.nagarro.todoapp.application.endpoint.model.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDto {

    private String task;

    private String status;
}
