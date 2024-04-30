package com.lista.app.rest.entities.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String description;
    private Boolean complete;
    //private User user;
}
