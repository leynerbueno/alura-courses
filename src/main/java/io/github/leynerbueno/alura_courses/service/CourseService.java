package io.github.leynerbueno.alura_courses.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.enums.Status;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.CourseRepository;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.CourseNpsDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.FilteredCoursesDTO;
import io.github.leynerbueno.alura_courses.rest.dto.course.ListCourseDTO;
import io.github.leynerbueno.alura_courses.service.event.CourseInactivatedEvent;
import io.github.leynerbueno.alura_courses.service.impl.CourseInterface;
import io.github.leynerbueno.alura_courses.util.AluraUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseInterface {

    private final CourseRepository repository;
    private final UserService userService;
    private final NpsService npsService;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher publisher;

    private AluraUtil aluraUtil = AluraUtil.getInstance();

    @Override
    @Transactional
    public CourseEntity insert(CourseDTO dto) {
        UserEntity instructor = userService.validate(dto.getInstructor(), Role.INSTRUCTOR);

        if (instructor.getRole().compareTo(Role.INSTRUCTOR) != 0) {
            throw new GeneralException("The user informed as an instructor is not registered as an instructor");
        }

        this.checkCodeAvaiable(dto.getCode());

        CourseEntity entity = new CourseEntity();
        entity.setCode(dto.getCode());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setInstructor(instructor);
        entity.setStatus(Status.ACTIVE);
        entity.setDtInsert(aluraUtil.getLocalDateTime());
        entity.setDtInactivate(null);

        return repository.save(entity);
    }

    @Override
    public CourseEntity find(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Course not found"));
    }

    @Override
    public CourseEntity findByCode(String code) {
        if (code == null) {
            throw new GeneralException("code is required");
        }

        return repository.findByCode(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Course not found"));
    }

    @Override
    public List<CourseEntity> filter(CourseEntity entity) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<CourseEntity> example = Example.of(entity, matcher);
        return repository.findAll(example);
    }

    @Override
    public FilteredCoursesDTO list(ListCourseDTO dto) {
        FilteredCoursesDTO courses = new FilteredCoursesDTO();

        if (dto.getPage() == null) {
            dto.setPage(0);
        } else {
            if (dto.getPage() > 0) {
                dto.setPage(dto.getPage() - 1);
            }
        }

        if (dto.getSize() == null) {
            dto.setSize(10);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());

        if (dto.getStatus() == null) {
            Page<CourseEntity> page = repository.findAll(pageable);

            courses.setContent(page.getContent());
            courses.setTotalElements(page.getTotalElements());
            courses.setTotalPages(page.getTotalPages());
            courses.setSize(page.getSize());
            return courses;
        }

        Page<CourseEntity> pages = repository.findByStatus(dto.getStatus(), pageable);

        courses.setContent(pages.getContent());
        courses.setTotalElements(pages.getTotalElements());
        courses.setTotalPages(pages.getTotalPages());
        courses.setSize(pages.getSize());
        return courses;
    }

    @Override
    public List<CourseNpsDTO> getReport() {
        List<CourseNpsDTO> report = new ArrayList<CourseNpsDTO>();
        List<CourseEntity> coursesWithRegistration = new ArrayList<CourseEntity>();
        List<CourseEntity> courses = repository.findAll();

        for (CourseEntity course : courses) {
            if (registrationService.listByCourse(course.getId()).size() > 2) {
                coursesWithRegistration.add(course);
            }
        }

        for (CourseEntity course : coursesWithRegistration) {
            String dsNps;
            Integer nps = npsService.calculateNps(course.getId());
            if (nps >= 75) {
                dsNps = "Excellent";

            } else if (nps >= 47 && nps <= 69) {
                dsNps = "Very good";

            } else if (nps >= 0 && nps <= 46) {
                dsNps = "Reasonable";

            } else {
                dsNps = "Bad";
            }

            CourseNpsDTO dto = new CourseNpsDTO();
            dto.setId(course.getId());
            dto.setName(course.getName());
            dto.setNps(nps);
            dto.setDsNps(dsNps);
            report.add(dto);
        }

        return report;
    }

    @Override
    @Transactional
    public CourseEntity update(CourseDTO dto) {
        Integer id = dto.getId();

        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id)
                .map(course -> {
                    UserEntity instructor = userService.validate(dto.getInstructor(), Role.INSTRUCTOR);

                    if (instructor.getRole().compareTo(Role.INSTRUCTOR) != 0) {
                        throw new GeneralException(
                                "The user informed as an instructor is not registered as an instructor");
                    }

                    CourseEntity entity = new CourseEntity();
                    entity.setId(id);
                    entity.setCode(course.getCode());
                    entity.setName(course.getName());
                    entity.setInstructor(instructor);
                    entity.setDescription(course.getDescription());
                    entity.setStatus(course.getStatus());
                    entity.setDtInsert(course.getDtInsert());
                    repository.save(entity);
                    return course;
                })
                .orElseThrow(() -> new GeneralException("Course not found"));
    }

    @Override
    @Transactional
    public void inactivate(String code) {
        CourseEntity entity = this.validateByCode(code);

        entity.setStatus(Status.INACTIVE);
        entity.setDtInactivate(aluraUtil.getLocalDateTime());
        repository.save(entity);

        publisher.publishEvent(new CourseInactivatedEvent(this, entity.getId()));
    }

    public CourseEntity validateByCode(String code) {
        if (code == null) {
            throw new GeneralException("code is required");
        }

        return repository.findByCode(code)
                .orElseThrow(() -> new GeneralException("Course not found"));
    }

    private void checkCodeAvaiable(String code) {
        Optional<CourseEntity> entity = repository.findByCode(code);
        if (entity.isPresent()) {
            throw new GeneralException("Code alredy in use");
        }
    }
}
