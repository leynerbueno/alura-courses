package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;
import java.util.Optional;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.rest.dto.user.UserDTO;

public interface UserInterface {

    public UserEntity insert(UserEntity entity);

    public Optional<UserEntity> find(Integer id);

    public Optional<UserDTO> findByUsername(String username);

    public List<UserEntity> filter(UserEntity entity);

    public UserEntity update(UserEntity entity);

    public void delete(Integer id);
}
