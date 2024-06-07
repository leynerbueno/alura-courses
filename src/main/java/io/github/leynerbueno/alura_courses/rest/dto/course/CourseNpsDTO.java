package io.github.leynerbueno.alura_courses.rest.dto.course;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseNpsDTO {

    private Integer id;
    private String name;
    private Integer nps;
    private String dsNps;
}
