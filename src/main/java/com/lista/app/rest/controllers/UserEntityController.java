package com.lista.app.rest.controllers;

import com.lista.app.rest.entities.Task;
import com.lista.app.rest.entities.dto.TaskDto;
import com.lista.app.rest.entities.dto.UserDto;
import com.lista.app.rest.servicies.UserEntityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityService service;
    public UserEntityController(UserEntityService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        if (!service.getUsers().isEmpty() && service.getUsers() != null){
            return ResponseEntity.status(HttpStatus.OK).body(service.getUsers());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> postUser(@RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> putUser(@RequestBody UserDto dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.putUser(dto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(){
        return ResponseEntity.ok(service.getCurrentUser());
    }

    @PostMapping("/me")
    public ResponseEntity<String> addTaskToUser(@RequestBody TaskDto dto, Authentication auth){
        return ResponseEntity.ok(service.addTaskToUser(dto, auth));
    }
    @GetMapping("/me/tasks")
    public ResponseEntity<List<TaskDto>> getUserTasks(Authentication auth){
        return ResponseEntity.ok(service.getUserTasks(auth));
    }

}


