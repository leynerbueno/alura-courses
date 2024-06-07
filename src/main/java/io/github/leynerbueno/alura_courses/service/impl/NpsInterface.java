package io.github.leynerbueno.alura_courses.service.impl;

import io.github.leynerbueno.alura_courses.entity.NpsEntity;
import io.github.leynerbueno.alura_courses.rest.dto.nps.NpsDTO;

public interface NpsInterface {

    public NpsEntity insert(NpsDTO dto);

}
