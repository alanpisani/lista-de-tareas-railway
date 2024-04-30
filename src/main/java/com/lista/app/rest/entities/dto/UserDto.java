package com.lista.app.rest.entities.dto;

import com.lista.app.rest.entities.RoleEntity;
import com.lista.app.rest.entities.Task;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username, password, name, surname, email;
    private LocalDateTime created_at, modified_at;
    private List<Task> tasks;
    private Boolean isEnable, accountNoExpired, accountNoLocked, credentialNoExpired;
    private Set<RoleEntity> roles;
}
