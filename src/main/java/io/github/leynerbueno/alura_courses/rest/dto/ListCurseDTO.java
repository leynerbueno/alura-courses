package io.github.leynerbueno.alura_courses.rest.dto;

import io.github.leynerbueno.alura_courses.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListCurseDTO {

    private Status status;
    private Integer page;
    private Integer size;
}
