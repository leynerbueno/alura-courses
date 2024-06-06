package io.github.leynerbueno.alura_courses.rest.controller;

import java.util.stream.Collectors;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;

import io.github.leynerbueno.alura_courses.exception.GeneralException;
import io.github.leynerbueno.alura_courses.rest.Errors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleGeneralExceptions(GeneralException e) {
        String errorMessage = e.getMessage();
        return new Errors(errorMessage);
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleDateTimeExceptions(DateTimeParseException e) {
        String errorMessage = e.getMessage();
        return new Errors(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleInvalidArgumentsException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new Errors(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ValueInstantiationException) {
            ValueInstantiationException ife = (ValueInstantiationException) cause;
            if (ife.getType().isEnumType()) {
                String originalMessage = ife.getOriginalMessage();
                return new Errors(extractRelevantMessage(originalMessage));
            }
        } else if (cause instanceof InvalidFormatException) {
            return new Errors("Invalid date format");
        }
        return new Errors(e.getMessage());
    }

    private String extractRelevantMessage(String originalMessage) {
        if (originalMessage == null) {
            return "Invalid input";
        }
        int index = originalMessage.indexOf("problem: ");
        if (index != -1) {
            return originalMessage.substring(index).replace("problem: ", "").trim();
        }
        return originalMessage;
    }
}
