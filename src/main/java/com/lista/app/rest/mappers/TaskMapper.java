package com.lista.app.rest.mappers;

import com.lista.app.rest.entities.Task;
import com.lista.app.rest.entities.dto.TaskDto;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TaskMapper {
    public static Task dtoToTask(TaskDto dto){
        return Task.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .complete(dto.getComplete())
                //.user(dto.getUser())
                .build();
    }
    public static TaskDto taskToDto(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .complete(task.getComplete())
                //.user(task.getUser())
                .build();
    }

}
