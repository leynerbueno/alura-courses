package io.github.leynerbueno.alura_courses.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.UserRepository;
import io.github.leynerbueno.alura_courses.rest.dto.UserDTO;
import io.github.leynerbueno.alura_courses.service.impl.UserInterface;

@Service
public class UserService implements UserInterface {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserEntity insert(UserEntity entity) {
        this.checkEmailAvaiable(entity.getEmail());
        this.checkUsernameAvaiable(entity.getUsername());

        entity.setEmail(entity.getEmail());
        entity.setUsername(entity.getUsername().toLowerCase());
        entity.setDtInsert(this.getLocalDateTime());

        return repository.save(entity);
    }

    @Override
    public UserEntity find(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new GeneralException("User not found"));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return repository.findByUsername(username)
                .map(user -> this.converter(user))
                .orElseThrow(() -> new GeneralException("User not found"));
    }

    @Override
    public List<UserEntity> filter(UserEntity entity) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<UserEntity> example = Example.of(entity, matcher);
        return repository.findAll(example);
    }

    @Override
    @Transactional
    public UserEntity update(UserEntity entity) {
        return repository.findById(entity.getId())
                .map(user -> {
                    if (entity.getEmail() != null && !user.getEmail().equals(entity.getEmail())) {
                        this.checkEmailAvaiable(entity.getEmail());
                        entity.setEmail(entity.getEmail());
                    }

                    if (entity.getUsername() != null &&
                            !user.getUsername().equalsIgnoreCase(entity.getUsername())) {
                        this.checkUsernameAvaiable(entity.getUsername());
                    }

                    entity.setRole(user.getRole());
                    entity.setPassword(user.getPassword());
                    entity.setDtInsert(user.getDtInsert());
                    repository.save(entity);
                    return user;
                })
                .orElseThrow(() -> new GeneralException("User not found"));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(user -> {
                    repository.delete(user);
                    return user;
                })
                .orElseThrow(() -> new GeneralException("User not found"));
    }

    private LocalDateTime getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
    }

    private void checkEmailAvaiable(String email) {
        Optional<UserEntity> entity = repository.findByEmail(email);
        if (entity.isPresent()) {
            throw new GeneralException("Email alredy in use");
        }
    }

    private void checkUsernameAvaiable(String username) {
        Optional<UserEntity> entity = repository.findByUsername(username.toLowerCase());
        if (entity.isPresent()) {
            throw new GeneralException("Username alredy in use");
        }
    }

    private UserDTO converter(UserEntity entity) {
        return UserDTO
                .builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}
