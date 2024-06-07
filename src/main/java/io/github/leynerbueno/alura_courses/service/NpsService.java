package io.github.leynerbueno.alura_courses.service;

import org.springframework.stereotype.Service;

import io.github.leynerbueno.alura_courses.entity.CourseEntity;
import io.github.leynerbueno.alura_courses.entity.NpsEntity;
import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.repository.NpsRepository;
import io.github.leynerbueno.alura_courses.rest.dto.nps.NpsDTO;
import io.github.leynerbueno.alura_courses.rest.dto.registration.RegistrationDTO;
import io.github.leynerbueno.alura_courses.sender.EmailSender;
import io.github.leynerbueno.alura_courses.service.impl.NpsInterface;
import io.github.leynerbueno.alura_courses.util.AluraUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NpsService implements NpsInterface {

    private final NpsRepository repository;
    private final UserService userService;
    private final CourseService courseService;
    private final RegistrationService registrationService;

    private AluraUtil aluraUtil = AluraUtil.getInstance();

    public NpsEntity validate(Integer id) {
        if (id == null) {
            throw new GeneralException("id is required");
        }

        return repository.findById(id)
                .orElseThrow(() -> new GeneralException("Nps not found"));
    }

    @Override
    @Transactional
    public NpsEntity insert(NpsDTO dto) {
        UserEntity student = userService.validate(dto.getStudent(), Role.STUDENT);
        CourseEntity course = courseService.validate(dto.getCourse());

        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setCourse(course.getId());
        registrationDTO.setStudent(student.getId());

        if (!registrationService.isStudentRegistrated(registrationDTO)) {
            throw new GeneralException(
                    "The student informed it's not registered in this course");
        }

        NpsEntity entity = new NpsEntity();
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setScore(this.validateScore(dto.getScore()));
        entity.setDsScore(dto.getDsScore());
        entity.setDtInsert(aluraUtil.getLocalDateTime());
        NpsEntity newNps = repository.save(entity);

        if (dto.getScore() < 6) {
            String instructorEmail = course.getInstructor().getEmail();
            String subject = "New score about the course: %s (ID: %s)".formatted(course.getName(), course.getId());
            String dsScore = dto.getDsScore() != null ? dto.getDsScore() : "---";
            String body = "Student: %s (ID: %s)\nScore: %s\nDescription: %s".formatted(student.getName(),
                    student.getId(),
                    dto.getScore(), dsScore);

            EmailSender.send(instructorEmail, subject, body);
        }
        return newNps;
    }

    private Integer validateScore(Integer score) {
        Integer newScore = score;
        if (score > 10) {
            newScore = 10;
        }

        if (score < 0) {
            newScore = 0;
        }

        return newScore;
    }
}
