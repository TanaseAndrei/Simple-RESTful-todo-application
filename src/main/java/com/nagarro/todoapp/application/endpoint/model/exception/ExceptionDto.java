package com.nagarro.todoapp.application.endpoint.model.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExceptionDto {
    private String message;
    private int status;
    private LocalDateTime thrownTime;
}
