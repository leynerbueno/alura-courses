package io.github.leynerbueno.alura_courses.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.rest.dto.user.UserDTO;
import io.github.leynerbueno.alura_courses.service.impl.UserInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserInterface service;

    @PostMapping("insert")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity insert(@RequestBody @Valid UserEntity entity) {
        return service.insert(entity);
    }

    @GetMapping("find")
    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity find(@RequestBody UserEntity entity) {
        return service.find(entity.getId());
    }

    @GetMapping("find-by-username")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO findByUsername(@RequestBody UserEntity entity) {
        return service.findDTOByUsername(entity.getUsername());
    }

    @GetMapping("filter")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserEntity> filter(@RequestBody UserEntity entity) {
        return service.filter(entity);
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity update(@RequestBody UserEntity entity) {
        return service.update(entity);
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody UserEntity entity) {
        service.delete(entity.getId());
    }
}