package com.lista.app.rest.controllers;

import com.lista.app.rest.entities.dto.TaskDto;
import com.lista.app.rest.exceptions.TaskNotFoundException;
import com.lista.app.rest.servicies.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
//@CrossOrigin(origins = "http://127.0.0.1:8080")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) { this.service = service; }
    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(){

        return ResponseEntity.ok(service.getTasks());

    }
    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        try {return ResponseEntity.ok(service.getTaskById(id));}
        catch (TaskNotFoundException e) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
    }
    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTask(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable Long id, @RequestBody TaskDto dto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateTaskById(id, dto));
        }
        catch(TaskNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        //return ResponseEntity.status(HttpStatus.OK).body(service.deleteTaskById(id));
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(service.deleteTaskById(id));
        }
        catch (TaskNotFoundException e) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
    }
}
