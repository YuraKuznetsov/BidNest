CREATE TABLE users
(
    user_id    BIGSERIAL     NOT NULL  PRIMARY KEY,
    email      VARCHAR(50)   NOT NULL  UNIQUE,
    password   VARCHAR(100)  NOT NULL,
    role       VARCHAR(30)   NOT NULL,
    firstname  VARCHAR(30)   NOT NULL,
    lastname   VARCHAR(30),
    country    VARCHAR(30),
    city       VARCHAR(30)
);