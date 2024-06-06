package io.github.leynerbueno.alura_courses.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class Errors {

    @Getter
    private List<String> erros;

    public Errors(String message) {
        this.erros = Arrays.asList(message);
    }

    public Errors(List<String> messages) {
        this.erros = messages;
    }
}
