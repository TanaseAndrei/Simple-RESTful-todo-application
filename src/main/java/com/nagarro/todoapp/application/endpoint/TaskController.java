package com.nagarro.todoapp.application.endpoint;

import com.nagarro.todoapp.application.endpoint.model.task.TaskDto;
import com.nagarro.todoapp.application.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(path = "/user/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> addTask(@PathVariable("id") Long userId,
                                        @RequestBody TaskDto taskDto){
        Long id = taskService.add(userId, taskDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{id}", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable("id") Long id){
        List<TaskDto> taskDtoList = taskService.getAll(id);
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @PatchMapping(path = "/{taskId}/user/{userId}", produces = "application/json")
    public ResponseEntity<TaskDto> completeTask(@PathVariable("taskId") Long taskId,
                                                @PathVariable("userId") Long userId){
        TaskDto taskDto = taskService.complete(userId, taskId);
        return new ResponseEntity<>(taskDto,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{taskId}/user/{userId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId,
                                     @PathVariable("userId") Long userId){
        taskService.delete(userId, taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
