package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.rest.dto.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.FilteredCoursesDTO;
import io.github.leynerbueno.alura_courses.rest.dto.ListCurseDTO;

public interface CourseInterface {

    CourseEntity insert(CourseDTO dto);

    CourseEntity find(Integer id);

    CourseEntity findByCode(String code);

    List<CourseEntity> filter(CourseEntity entity);

    FilteredCoursesDTO list(ListCurseDTO dto);

    CourseEntity update(CourseDTO dto);

    void inactivate(String code);
}
