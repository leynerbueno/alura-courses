package io.github.leynerbueno.alura_courses.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException(
                "Invalid Status: " + value + ". Accepted values are: " + Arrays.toString(values()));
    }

    @JsonValue
    public String toValue() {
        return name();
    }

    public static Status unformat(String status) {
        try {
            return valueOf(status.replace("-", "_"));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> list() {
        List<String> values = new ArrayList<>();
        for (Status status : Status.values()) {
            values.add(status.name());
        }
        return values;
    }

    public static String toLine() {
        return String.join(", ", list());
    }
}
