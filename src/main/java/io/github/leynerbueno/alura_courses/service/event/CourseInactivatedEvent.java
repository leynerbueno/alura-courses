package io.github.leynerbueno.alura_courses.service.event;

import org.springframework.context.ApplicationEvent;

public class CourseInactivatedEvent extends ApplicationEvent {
    private Integer courseId;

    public CourseInactivatedEvent(Object source, Integer courseId) {
        super(source);
        this.courseId = courseId;
    }

    public Integer getCourseId() {
        return courseId;
    }
}
