package io.github.leynerbueno.alura_courses.service.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.github.leynerbueno.alura_courses.service.RegistrationService;
import io.github.leynerbueno.alura_courses.service.event.CourseInactivatedEvent;

@Component
public class RegistrationEventListener {

    @Autowired
    private RegistrationService registrationService;

    @EventListener
    public void handleCourseInactivatedEvent(CourseInactivatedEvent event) {
        registrationService.deleteByCourse(event.getCourseId());
    }
}
