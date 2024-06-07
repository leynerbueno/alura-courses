package io.github.leynerbueno.alura_courses.rest.dto.course;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FilteredCoursesDTO {

    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private List<CourseEntity> content;
}
