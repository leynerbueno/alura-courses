CREATE TABLE IF NOT EXISTS REGISTRATION (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_id INTEGER REFERENCES USER_SYSTEM (ID) NOT NULL,
    course_id INTEGER REFERENCES COURSE (ID) NOT NULL,
    dt_registration TIMESTAMP NOT NULL
);