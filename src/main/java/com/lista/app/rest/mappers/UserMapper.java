package com.lista.app.rest.mappers;

import com.lista.app.rest.entities.UserEntity;
import com.lista.app.rest.entities.dto.UserDto;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserEntity dtoToUser(UserDto dto){
        return UserEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .created_at(LocalDateTime.now())
                .modified_at(LocalDateTime.now())
                .tasks(dto.getTasks())
                .accountNoExpired(dto.getAccountNoExpired())
                .accountNoLocked(dto.getAccountNoLocked())
                .credentialNoExpired(dto.getCredentialNoExpired())
                .isEnable(dto.getIsEnable())
                .roles(dto.getRoles())
                .build();
    }
    public static UserDto userToDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .created_at(user.getCreated_at())
                .modified_at(user.getModified_at())
                .tasks(user.getTasks())
                .accountNoExpired(user.getAccountNoExpired())
                .accountNoLocked(user.getAccountNoLocked())
                .credentialNoExpired(user.getCredentialNoExpired())
                .isEnable(user.getIsEnable())
                .roles(user.getRoles())
                .build();
    }
}
