CREATE TABLE user_entity
(
    id       BIGINT NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_userentity PRIMARY KEY (id)
);