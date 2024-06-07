package io.github.leynerbueno.alura_courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.UserRepository;
import io.github.leynerbueno.alura_courses.rest.dto.user.UserDTO;
import io.github.leynerbueno.alura_courses.service.impl.UserInterface;
import io.github.leynerbueno.alura_courses.util.AluraUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private final UserRepository repository;
    private AluraUtil aluraUtil = AluraUtil.getInstance();

    public UserEntity validate(Integer id, Role role) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        String message = String.format("%s not found", role.name());

        return repository.findById(id)
                .orElseThrow(() -> new GeneralException(message));
    }

    @Override
    @Transactional
    public UserEntity insert(UserEntity entity) {
        this.checkEmailAvaiable(entity.getEmail());
        this.checkUsernameAvaiable(entity.getUsername());

        entity.setEmail(entity.getEmail());
        entity.setUsername(entity.getUsername().toLowerCase());
        entity.setDtInsert(aluraUtil.getLocalDateTime());

        return repository.save(entity);
    }

    @Override
    public UserEntity find(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User not found"));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return repository.findByUsername(username)
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto = this.converter(user);
                    return dto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found"));
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
        Integer id = entity.getId();

        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id)
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
        if (id == null) {
            throw new GeneralException("id is required");
        }

        repository.findById(id)
                .map(user -> {
                    repository.delete(user);
                    return user;
                })
                .orElseThrow(() -> new GeneralException("User not found"));
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
