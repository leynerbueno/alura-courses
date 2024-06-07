package io.github.leynerbueno.alura_courses.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.leynerbueno.alura_courses.entity.NpsEntity;
import io.github.leynerbueno.alura_courses.rest.dto.nps.NpsDTO;
import io.github.leynerbueno.alura_courses.service.NpsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/nps")
public class NpsController {

    private NpsService service;

    @PostMapping("insert")
    @ResponseStatus(HttpStatus.CREATED)
    public NpsEntity insert(@RequestBody @Valid NpsDTO dto) {
        return service.insert(dto);
    }
}
