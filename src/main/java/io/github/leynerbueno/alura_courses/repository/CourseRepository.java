package io.github.leynerbueno.alura_courses.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.enums.Status;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    public Optional<CourseEntity> findByCode(String code);

    public Page<CourseEntity> findByStatus(Status status, Pageable pageable);

}
