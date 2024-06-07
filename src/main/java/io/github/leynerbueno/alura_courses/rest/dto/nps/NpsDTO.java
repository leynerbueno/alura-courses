package io.github.leynerbueno.alura_courses.rest.dto.nps;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NpsDTO {

    @NotNull(message = "student is required")
    private Integer student;

    @NotNull(message = "course is required")
    private Integer course;

    @NotNull(message = "score is required")
    private Integer score;

    private String dsScore;
}
