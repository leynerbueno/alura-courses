package io.github.leynerbueno.alura_courses.service.validate;

import org.springframework.stereotype.Service;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseValidationService {

    private final CourseRepository repository;

    public CourseEntity validate(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id)
                .orElseThrow(() -> new GeneralException("Course not found"));
    }
}
