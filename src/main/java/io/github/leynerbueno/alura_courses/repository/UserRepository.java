package io.github.leynerbueno.alura_courses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import io.github.leynerbueno.alura_courses.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);
}
