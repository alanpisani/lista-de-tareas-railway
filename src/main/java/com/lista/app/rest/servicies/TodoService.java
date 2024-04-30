package com.lista.app.rest.servicies;


import com.lista.app.rest.entities.Task;
import com.lista.app.rest.entities.dto.TaskDto;
import com.lista.app.rest.exceptions.TaskNotFoundException;
import com.lista.app.rest.mappers.TaskMapper;
import com.lista.app.rest.repositories.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    //private final UserRepository userRepository;

    public List<TaskDto> getTasks(){
        return todoRepository.findAll().stream()
                .map(TaskMapper::taskToDto)
                .collect(Collectors.toList());
    }
    public TaskDto getTaskById(Long id){
        Optional<Task> oTask= todoRepository.findById(id);
        if(oTask.isPresent()){
            Task task = oTask.get();
            return TaskMapper.taskToDto(task);
        }
        throw new TaskNotFoundException("No se encontró ninguna tarea con el ID: " + id);
    }

    public TaskDto saveTask(TaskDto dto){
        Task task = TaskMapper.dtoToTask(dto);
        task.setComplete(false);
        todoRepository.save(task);
        return dto;
    }

    public String updateTaskById(Long id, TaskDto dto){
        Optional<Task> oTaskToModify = todoRepository.findById(id);
        if (oTaskToModify.isPresent()){
            Task taskToModify = oTaskToModify.get();
            if (dto.getDescription() != null){
                taskToModify.setDescription(dto.getDescription());
            }
            if(dto.getComplete() != null){
                taskToModify.setComplete(dto.getComplete());
            }
            todoRepository.save(taskToModify);
            return "Task updated";

        }
        throw new TaskNotFoundException("No se encontró ninguna tarea con el ID: " + id);

    }

    public String deleteTaskById(Long id){
        Optional<Task> taskToDelete = todoRepository.findById(id);
        if(taskToDelete.isPresent()){
            todoRepository.deleteById(id);
            return "Task deleted";
        }
        throw new TaskNotFoundException("No se encontró ninguna tarea con el ID: " + id);
    }

}
