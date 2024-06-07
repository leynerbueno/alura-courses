package io.github.leynerbueno.alura_courses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.leynerbueno.alura_courses.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {

    public RegistrationEntity findByStudentIdAndCourseId(Integer studentId, Integer courseId);

    public List<RegistrationEntity> findByCourseId(Integer courseId);
}
