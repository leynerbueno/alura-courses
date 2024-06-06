package io.github.leynerbueno.alura_courses.validation.constraint;

import io.github.leynerbueno.alura_courses.enums.Role;
import io.github.leynerbueno.alura_courses.validation.ValidRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        if (role != null) {
            for (String enumValue : Role.list()) {
                if (role.name().equals(enumValue)) {
                    return true;
                }
            }
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Role must have one of the values " + Role.toLine())
                .addConstraintViolation();
        return false;
    }
}
