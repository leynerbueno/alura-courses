package io.github.leynerbueno.alura_courses.entity;

import java.time.LocalDateTime;

import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.validation.ValidRole;
import io.github.leynerbueno.alura_courses.validation.ValidUsername;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_system")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull()
    @Column(name = "name", length = 150)
    private String name;

    @NotNull(message = "username is required")
    @Column(name = "username", length = 20, unique = true)
    @ValidUsername
    private String username;

    @Email(message = "provide a valid email")
    @NotNull(message = "email is required")
    @Column(name = "email", length = 50, unique = true)
    private String email;

    @NotNull(message = "password is required")
    @Column(name = "password", length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "role is required")
    @Column(name = "role")
    @ValidRole
    private Role role;

    @Column(name = "dt_insert")
    private LocalDateTime dtInsert;

}
