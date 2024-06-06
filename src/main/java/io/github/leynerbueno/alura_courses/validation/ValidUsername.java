package io.github.leynerbueno.alura_courses.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.leynerbueno.alura_courses.validation.constraint.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernameValidator.class)
public @interface ValidUsername {

    String message() default "Username must not contain numbers or spaces";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}