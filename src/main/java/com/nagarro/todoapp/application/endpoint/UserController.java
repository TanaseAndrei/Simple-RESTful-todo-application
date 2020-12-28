package com.nagarro.todoapp.application.endpoint;

import com.nagarro.todoapp.application.endpoint.model.user.UserDto;
import com.nagarro.todoapp.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto){
        Long id = userService.add(userDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto userDto = userService.get(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(null);
    }

}
