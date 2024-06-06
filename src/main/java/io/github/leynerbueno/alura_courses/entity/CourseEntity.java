package io.github.leynerbueno.alura_courses.entity;

import java.time.LocalDateTime;

import io.github.leynerbueno.alura_courses.enums.Status;
import io.github.leynerbueno.alura_courses.validation.ValidCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "code is required")
    @Column(name = "code", length = 20, unique = true)
    @ValidCode
    private String code;

    @NotNull(message = "name is required")
    @Column(name = "name", length = 150)
    private String name;

    @ManyToOne
    @NotNull(message = "instructor is required")
    @JoinColumn(name = "instructor_id")
    private UserEntity instructor;

    @Column(name = "description", length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;

    @Column(name = "dt_delete")
    private LocalDateTime dtDelete;

    @Column(name = "dt_insert")
    private LocalDateTime dtInsert;
}
