package io.github.leynerbueno.alura_courses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.leynerbueno.alura_courses.entity.NpsEntity;

public interface NpsRepository extends JpaRepository<NpsEntity, Integer> {

    public List<NpsEntity> findByCourseId(Integer courseId);
}
