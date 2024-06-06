package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.rest.dto.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.FilteredCoursesDTO;

public interface CourseInterface {

    CourseEntity insert(CourseDTO courseDTO);

    CourseEntity find(Integer id);

    CourseEntity findByCode(String code);

    List<CourseEntity> filter(CourseEntity entity);

    FilteredCoursesDTO list(CourseDTO dto);

    CourseEntity update(CourseEntity entity);

    void inactivate(String code);
}
