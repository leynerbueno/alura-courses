package io.github.leynerbueno.alura_courses.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.leynerbueno.alura_courses.validation.constraint.CodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CodeValidator.class)
public @interface ValidCode {
    String message() default "Code must not contain numbers, spaces or special characterss";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
