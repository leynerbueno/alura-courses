package io.github.leynerbueno.alura_courses.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.entity.RegistrationEntity;
import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.enums.Status;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.RegistrationRepository;
import io.github.leynerbueno.alura_courses.rest.dto.registration.RegistrationDTO;
import io.github.leynerbueno.alura_courses.service.impl.RegistrationInterface;
import io.github.leynerbueno.alura_courses.service.validate.CourseValidationService;
import io.github.leynerbueno.alura_courses.util.AluraUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService implements RegistrationInterface {

    private final RegistrationRepository repository;
    private final UserService userService;
    private final CourseValidationService courseService;
    private AluraUtil aluraUtil = AluraUtil.getInstance();

    public RegistrationEntity validate(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id)
                .orElseThrow(() -> new GeneralException("Registration not found"));
    }

    @Override
    @Transactional
    public RegistrationEntity insert(RegistrationDTO dto) {
        UserEntity student = userService.validate(dto.getStudent(), Role.STUDENT);

        CourseEntity course = courseService.validate(dto.getCourse());

        if (course.getStatus().compareTo(Status.ACTIVE) != 0) {
            throw new GeneralException(
                    "This course is " + course.getStatus());
        }

        if (course.getInstructor().getId() == student.getId()) {
            throw new GeneralException(
                    "The student informed can't be registered in this course because he it's the instructor");
        }

        this.checkStudentRegistration(dto);

        RegistrationEntity entity = new RegistrationEntity();
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setDtRegistration(aluraUtil.getLocalDateTime());
        return repository.save(entity);
    }

    @Override
    public RegistrationEntity find(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Registration not found"));
    }

    @Override
    public List<RegistrationEntity> listByCourse(Integer courseId) {
        CourseEntity course = courseService.validate(courseId);

        RegistrationEntity entity = new RegistrationEntity();
        entity.setCourse(course);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<RegistrationEntity> example = Example.of(entity, matcher);
        return repository.findAll(example);
    }

    @Override
    public List<RegistrationEntity> listByStudent(Integer studentId) {
        UserEntity user = userService.validate(studentId, Role.STUDENT);

        RegistrationEntity entity = new RegistrationEntity();
        entity.setStudent(user);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<RegistrationEntity> example = Example.of(entity, matcher);
        return repository.findAll(example);
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        repository.findById(id)
                .map(registration -> {
                    repository.delete(registration);
                    return registration;
                })
                .orElseThrow(() -> new GeneralException("Registration not found"));
    }

    public void deleteByCourse(Integer courseId) {
        List<RegistrationEntity> registrations = repository.findByCourseId(courseId);
        repository.deleteAll(registrations);
    }

    public void checkStudentRegistration(RegistrationDTO dto) {
        if (this.isStudentRegistrated(dto)) {
            throw new GeneralException(
                    "The student informed it's already registered in this course");
        }
    }

    public Boolean isStudentRegistrated(RegistrationDTO dto) {
        RegistrationEntity registration = repository.findByStudentIdAndCourseId(dto.getStudent(),
                dto.getCourse());

        if (registration != null) {
            return true;
        }

        return false;
    }
}
