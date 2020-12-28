package com.nagarro.todoapp.application.exception.handler;

import com.nagarro.todoapp.application.endpoint.model.exception.ExceptionDto;
import com.nagarro.todoapp.application.exception.AppException;
import com.nagarro.todoapp.application.service.converter.ExceptionConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {

    private final ExceptionConverter exceptionConverter;

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<ExceptionDto> handleException(AppException appException){
        ExceptionDto exceptionDto = exceptionConverter.convertToDto(appException);
        return new ResponseEntity<>(exceptionDto,appException.getStatus());
    }
}
