package com.nagarro.todoapp.application.service.converter;

import com.nagarro.todoapp.application.endpoint.model.user.UserDto;
import com.nagarro.todoapp.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserConverter {

    private final TaskConverter taskConverter;

    public User convertToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setCity(userDto.getCity());
        user.setCountry(userDto.getCountry());
        user.setEmail(userDto.getEmail());
        user.setTaskList(Collections.emptyList());
        return user;
    }

    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setTaskList(taskConverter.convertToDtoList(user.getTaskList()));
        return userDto;
    }

    public List<UserDto> convertToDtoList(List<User> userList) {
        return userList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
