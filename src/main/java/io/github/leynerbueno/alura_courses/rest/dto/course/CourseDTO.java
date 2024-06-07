package io.github.leynerbueno.alura_courses.rest.dto.course;

import java.time.LocalDateTime;

import io.github.leynerbueno.alura_courses.enums.Status;
import io.github.leynerbueno.alura_courses.validation.ValidCode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {

    private Integer id;

    @NotNull(message = "code is required")
    @ValidCode
    private String code;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "instructor is required")
    private Integer instructor;

    private String description;
    private Status status;
    private LocalDateTime dtDelete;
    private LocalDateTime dtInsert;
    private Integer page;
    private Integer size;
}
