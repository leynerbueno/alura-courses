package io.github.leynerbueno.alura_courses.rest.dto.registration;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDTO {

    @NotNull(message = "student is required")
    private Integer student;

    @NotNull(message = "course is required")
    private Integer course;

}
