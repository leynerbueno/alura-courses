package io.github.leynerbueno.alura_courses.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "nps")
public class NpsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "score")
    private Integer score;

    @Column(name = "ds_score", length = 200)
    private String dsScore;

    @Column(name = "dt_insert")
    private LocalDateTime dtInsert;

}
