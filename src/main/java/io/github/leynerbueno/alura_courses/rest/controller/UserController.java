package io.github.leynerbueno.alura_courses.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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
    public Optional<UserEntity> find(@RequestBody UserEntity entity) {
        return service.find(entity.getId());
    }

    @GetMapping("find-by-username")
    public Optional<UserDTO> findByUsername(@RequestBody UserEntity entity) {
        return service.findByUsername(entity.getUsername());
    }

    @GetMapping("filter")
    public List<UserEntity> filter(@RequestBody UserEntity entity) {
        return service.filter(entity);
    }

    @PutMapping("update")
    public UserEntity update(@RequestBody UserEntity entity) {
        return service.update(entity);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody UserEntity entity) {
        service.delete(entity.getId());
    }
}