package io.github.leynerbueno.alura_courses.rest.dto;

import io.github.leynerbueno.alura_courses.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String email;
    private Role role;
}
