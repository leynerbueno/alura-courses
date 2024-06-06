package io.github.leynerbueno.alura_courses.validation.constraint;

import java.util.regex.Pattern;

import io.github.leynerbueno.alura_courses.validation.ValidCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodeValidator implements ConstraintValidator<ValidCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = ".*[\\d\\s!@#$%^&*()_+=\\[\\]{}|;:'\",.<>?/~`].*";
        Pattern pattern = Pattern.compile(regex);
        return value != null && !value.isEmpty() && !pattern.matcher(value).matches();
    }
}
