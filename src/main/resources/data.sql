CREATE TABLE user_entity
(
    id       BIGINT NOT NULL,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    uuid     VARCHAR(255) NOT NULL ,
    CONSTRAINT pk_user_entity PRIMARY KEY (id)
);