package com.nagarro.todoapp.application.service.converter;

import com.nagarro.todoapp.application.endpoint.model.exception.ExceptionDto;
import com.nagarro.todoapp.application.exception.AppException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExceptionConverter {

    public ExceptionDto convertToDto(AppException appException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setStatus(appException.getStatus().value());
        exceptionDto.setMessage(appException.getMessage());
        exceptionDto.setThrownTime(LocalDateTime.now());
        return exceptionDto;
    }
}
