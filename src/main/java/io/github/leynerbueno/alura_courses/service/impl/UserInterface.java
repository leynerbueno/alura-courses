package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.rest.dto.user.UserDTO;

public interface UserInterface {

    public UserEntity insert(UserEntity entity);

    public UserEntity find(Integer id);

    public UserDTO findDTOByUsername(String username);

    public List<UserEntity> filter(UserEntity entity);

    public UserEntity update(UserEntity entity);

    public void delete(Integer id);
}
