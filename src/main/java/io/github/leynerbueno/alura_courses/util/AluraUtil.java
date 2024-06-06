package io.github.leynerbueno.alura_courses.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AluraUtil {

    private static AluraUtil instance;

    public synchronized static AluraUtil getInstance() {
        if (instance == null) {
            instance = new AluraUtil();
        }
        return instance;
    }

    public LocalDateTime getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
    }
}
