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

import io.github.leynerbueno.alura_courses.entity.RegistrationEntity;
import io.github.leynerbueno.alura_courses.rest.dto.registration.RegistrationDTO;
import io.github.leynerbueno.alura_courses.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationService service;

    @PostMapping("insert")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationEntity insert(@RequestBody @Valid RegistrationDTO dto) {
        return service.insert(dto);
    }

    @GetMapping("find")
    @PreAuthorize("hasRole('ADMIN')")
    public RegistrationEntity find(@RequestBody RegistrationEntity entity) {
        return service.find(entity.getId());
    }

    @GetMapping("list-by-course")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RegistrationEntity> listByCourse(@RequestBody RegistrationDTO dto) {
        return service.listByCourse(dto.getCourse());
    }

    @GetMapping("list-by-student")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RegistrationEntity> listByStudent(@RequestBody RegistrationDTO dto) {
        return service.listByStudent(dto.getStudent());
    }

    @PutMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestBody RegistrationEntity entity) {
        service.delete(entity.getId());
    }
}
