package com.lista.app.rest.servicies;

import com.lista.app.rest.entities.Task;
import com.lista.app.rest.entities.UserEntity;
import com.lista.app.rest.entities.dto.TaskDto;
import com.lista.app.rest.entities.dto.UserDto;
import com.lista.app.rest.mappers.TaskMapper;
import com.lista.app.rest.mappers.UserMapper;
import com.lista.app.rest.repositories.TodoRepository;
import com.lista.app.rest.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEntityService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }
    public UserDto getUserById(Long id){
        return UserMapper.userToDto(userRepository.findById(id).get());
    }
    public UserDto saveUser(UserDto dto){
        dto.setAccountNoExpired(true);
        dto.setAccountNoLocked(true);
        dto.setCredentialNoExpired(true);
        dto.setIsEnable(true);
        UserEntity savedUser = userRepository.save(UserMapper.dtoToUser(dto));
        return UserMapper.userToDto(savedUser);
    }
    public UserDto putUser(UserDto dto, Long id){
        UserEntity userToModify = userRepository.findById(id).get();
        if(dto.getPassword() != null){
            userToModify.setPassword(dto.getPassword());
        }
        if (dto.getName() != null){
            userToModify.setName(dto.getName());
        }
        if(dto.getSurname() != null){
            userToModify.setSurname(dto.getSurname());
        }
        if(dto.getEmail() != null){
            userToModify.setEmail(dto.getEmail());
        }
        userToModify.setModified_at(LocalDateTime.now());
        UserEntity userModified = userRepository.save(userToModify);
        return UserMapper.userToDto(userModified);
    }
    public String deleteUserById(Long id){
        userRepository.deleteById(id);
        return "User deleted";
    }

    public String getCurrentUser() {
        // Obtiene el objeto Authentication del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica si el usuario está autenticado
        if (authentication != null && authentication.isAuthenticated()) {

            // Obtiene el nombre de usuario del objeto Authentication
            String username = authentication.getName();
            return "Usuario logueado: " + username;

        }else {
            return "Ningún usuario autenticado";
        }
    }
//
    public String addTaskToUser(TaskDto taskDto, Authentication auth){
        UserEntity user = userRepository.findUserEntityByUsername(auth.getName()).get();
        Task task = TaskMapper.dtoToTask(taskDto);
        task.setComplete(false);
        task.setUser(user);
        user.getTasks().add(task);
        userRepository.save(user); // Guardar en el repositorio de usuarios en lugar del repositorio de tareas

        return "Tarea añadida exitosamente para el usuario " + auth.getName();
    }
    public List<TaskDto> getUserTasks(Authentication auth){
        UserEntity user = userRepository.findUserEntityByUsername(auth.getName()).get();
        return user.getTasks().stream()
                .map(TaskMapper::taskToDto)
                .collect(Collectors.toList());
    }

}
