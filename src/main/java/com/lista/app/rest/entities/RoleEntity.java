package com.lista.app.rest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Rol")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="permisos_rol", joinColumns = @JoinColumn(name="id_rol"), inverseJoinColumns = @JoinColumn(name = "id_permiso"))
    private Set<PermissionEntity> permissionsList = new HashSet<>();
}
