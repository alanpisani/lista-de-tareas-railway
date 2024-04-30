package com.lista.app.rest.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre_usuario", "email"}))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario")
    private String username;
    @Column(name = "contrasena")
    private String password;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String surname;
    @Column
    private String email;
    @Column(name = "fecha_creacion")
    private LocalDateTime created_at;
    @Column(name = "fecha_modificacion")
    private LocalDateTime modified_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Column(name = "disponible")
    private Boolean isEnable = true;
    @Column(name = "no_caducada")
    private Boolean accountNoExpired = true;
    @Column(name = "no_bloqueada")
    private Boolean accountNoLocked = true;
    @Column(name = "no_credenciales_caducados")
    private Boolean credentialNoExpired = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="roles_usuario", joinColumns = @JoinColumn(name="id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<RoleEntity> roles = new HashSet<>();

}
