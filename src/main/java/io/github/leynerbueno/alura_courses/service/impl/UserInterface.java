package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.rest.dto.UserDTO;

public interface UserInterface {

    UserEntity insert(UserEntity entity);

    UserEntity find(Integer id);

    UserDTO findByUsername(String username);

    List<UserEntity> filter(UserEntity entity);

    UserEntity update(UserEntity entity);

    void delete(Integer id);
}
