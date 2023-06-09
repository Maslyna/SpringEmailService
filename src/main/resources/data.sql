CREATE TABLE user_entity
(
    user_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login     VARCHAR(255),
    password  VARCHAR(255),
    uuid      VARCHAR(255),
    authority VARCHAR(255),
    CONSTRAINT pk_user_entity PRIMARY KEY (user_id)
);

CREATE TABLE deleted_user
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    time_to_delete TIMESTAMP,
    user_id        BIGINT,
    CONSTRAINT pk_deleted_user PRIMARY KEY (id)
);

ALTER TABLE deleted_user
    ADD CONSTRAINT FK_DELETED_USER_ON_USER FOREIGN KEY (user_id) REFERENCES user_entity (user_id);