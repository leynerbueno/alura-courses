package io.github.leynerbueno.alura_courses.rest.dto.user;

import java.time.LocalDateTime;

import io.github.leynerbueno.alura_courses.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime dtInsert;
}
