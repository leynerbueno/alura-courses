package io.github.leynerbueno.alura_courses.rest.dto;

import java.time.LocalDateTime;

import io.github.leynerbueno.alura_courses.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;
    private String code;
    private String name;
    private Integer instructor;
    private String description;
    private Status status;
    private LocalDateTime dtDelete;
    private LocalDateTime dtInsert;
    private Integer page;
    private Integer size;
}
