CREATE TABLE USER_SYSTEM(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150),
    username UNIQUE VARCHAR(20),
    email UNIQUE VARCHAR(20),
    password VARCHAR (150),
    role VARCHAR(10),
    dt_insert TIMESTAMP
);