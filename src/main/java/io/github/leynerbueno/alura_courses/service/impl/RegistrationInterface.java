package io.github.leynerbueno.alura_courses.service.impl;

import java.util.List;
import java.util.Optional;

import io.github.leynerbueno.alura_courses.entity.RegistrationEntity;
import io.github.leynerbueno.alura_courses.rest.dto.registration.RegistrationDTO;

public interface RegistrationInterface {

    public RegistrationEntity insert(RegistrationDTO dto);

    public Optional<RegistrationEntity> find(Integer id);

    public List<RegistrationEntity> listByCourse(Integer courseId);

    public List<RegistrationEntity> listByStudent(Integer studentId);

    public void delete(Integer id);
}
