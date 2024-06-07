package io.github.leynerbueno.alura_courses.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseNpsDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.FilteredCoursesDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.ListCourseDTO;
import io.github.leynerbueno.alura_courses.service.impl.CourseInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private CourseInterface service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("insert")
    @PreAuthorize("hasRole('ADMIN')")
    public CourseEntity insert(@RequestBody @Valid CourseDTO dto) {
        return service.insert(dto);
    }

    @GetMapping("find")
    @PreAuthorize("hasRole('ADMIN')")
    public CourseEntity find(@RequestBody CourseEntity entity) {
        return service.find(entity.getId());
    }

    @GetMapping("find-by-code")
    @PreAuthorize("hasRole('ADMIN')")
    public CourseEntity findByCode(@RequestBody CourseEntity entity) {
        return service.findByCode(entity.getCode());
    }

    @GetMapping("get-report")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CourseNpsDTO> getReport() {
        return service.getReport();
    }

    @GetMapping("filter")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CourseEntity> filter(@RequestBody CourseEntity entity) {
        return service.filter(entity);
    }

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public FilteredCoursesDTO list(@RequestBody ListCourseDTO dto) {
        return service.list(dto);
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public CourseEntity update(@RequestBody @Valid CourseDTO dto) {
        return service.update(dto);
    }

    @PutMapping("inactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public void inactivate(@RequestBody CourseEntity entity) {
        service.inactivate(entity.getCode());
    }
}
