package io.github.leynerbueno.alura_courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.leynerbueno.alura_courses.entity.NpsEntity;

public interface NpsRepository extends JpaRepository<NpsEntity, Integer> {

}
