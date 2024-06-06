package io.github.leynerbueno.alura_courses.validation.constraint;

import java.util.regex.Pattern;

import io.github.leynerbueno.alura_courses.validation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = ".*[\\d\\s].*";
        Pattern pattern = Pattern.compile(regex);
        return value != null && !pattern.matcher(value).matches();
    }

}
