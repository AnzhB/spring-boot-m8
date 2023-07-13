DROP TABLE IF EXISTS persons;

CREATE TABLE persons(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO persons(name, email)
VALUES ('user1', 'user1@gmail.com'),
       ('user2', 'user2@gmail.com'),
       ('user3', 'user3@gmail.com');
