package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseNpsDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.FilteredCoursesDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.ListCourseDTO;

public interface CourseInterface {

    public CourseEntity insert(CourseDTO dto);

    public CourseEntity find(Integer id);

    public CourseEntity findByCode(String code);

    public List<CourseEntity> filter(CourseEntity entity);

    public FilteredCoursesDTO list(ListCourseDTO dto);

    public List<CourseNpsDTO> getReport();

    public CourseEntity update(CourseDTO dto);

    public void inactivate(String code);
}
