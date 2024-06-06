package io.github.leynerbueno.alura_courses.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    @JsonCreator
    public static Role fromValue(String value) {
        for (Role role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException(
                "Invalid role: " + value + ". Accepted values are: " + Arrays.toString(values()));
    }

    @JsonValue
    public String toValue() {
        return name();
    }

    public static Role unformat(String role) {
        try {
            return valueOf(role.replace("-", "_"));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> list() {
        List<String> values = new ArrayList<>();
        for (Role role : Role.values()) {
            values.add(role.name());
        }
        return values;
    }

    public static String toLine() {
        return String.join(", ", list());
    }
}
