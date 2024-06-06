package io.github.leynerbueno.alura_courses.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.rest.dto.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.FilteredCoursesDTO;
import io.github.leynerbueno.alura_courses.service.impl.CourseInterface;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private CourseInterface service;

    public CourseController(CourseInterface service) {
        this.service = service;
    }

    @PostMapping("insert")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseEntity insert(@RequestBody @Valid CourseDTO dto) {
        return service.insert(dto);
    }

    @GetMapping("find")
    public CourseEntity find(@RequestBody CourseEntity entity) {
        return service.find(entity.getId());
    }

    @GetMapping("find-by-code")
    public CourseEntity findByCode(@RequestBody CourseEntity entity) {
        return service.findByCode(entity.getCode());
    }

    @GetMapping("filter")
    public List<CourseEntity> filter(@RequestBody CourseEntity entity) {
        return service.filter(entity);
    }

    @GetMapping("list")
    public FilteredCoursesDTO list(@RequestBody CourseDTO dto) {
        return service.list(dto);
    }

    @PutMapping("update")
    public CourseEntity update(@RequestBody CourseEntity entity) {
        return service.update(entity);
    }

    @PutMapping("inactivate")
    public void inactivate(@RequestBody CourseEntity entity) {
        service.inactivate(entity.getCode());
    }
}
