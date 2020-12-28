package com.nagarro.todoapp.application.service;

import com.nagarro.todoapp.application.endpoint.model.user.UserDto;
import com.nagarro.todoapp.application.exception.AppException;
import com.nagarro.todoapp.application.service.converter.UserConverter;
import com.nagarro.todoapp.domain.entity.User;
import com.nagarro.todoapp.infrastructure.gateway.UserJpaRepositoryGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private static final String USER_NOT_FOUND_MESSAGE = "The user has not been found";

    private final UserJpaRepositoryGateway userJpaRepositoryGateway;
    private final UserConverter userConverter;

    public Long add(UserDto userDto) {
        User user = userConverter.convertToUser(userDto);
        user.setRegistrationDate(LocalDateTime.now());
        return userJpaRepositoryGateway.save(user).getId();
    }

    public UserDto get(Long id) {
        User user = userJpaRepositoryGateway.findById(id).orElseThrow(() -> new AppException(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
        return userConverter.convertToDto(user);
    }

    public List<UserDto> getAll() {
        List<User> userList = userJpaRepositoryGateway.findAll();
        return userConverter.convertToDtoList(userList);
    }

    public void delete(Long id) {
        User user = userJpaRepositoryGateway.findById(id).orElseThrow(() -> new AppException(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND));
        userJpaRepositoryGateway.delete(user);
    }
}
