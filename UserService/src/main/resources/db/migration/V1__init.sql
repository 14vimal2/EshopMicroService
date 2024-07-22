CREATE TABLE address
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    deleted    BIT(1)                NOT NULL,
    street     VARCHAR(255)          NULL,
    city       VARCHAR(255)          NULL,
    state      VARCHAR(255)          NULL,
    zip        VARCHAR(255)          NULL,
    country    VARCHAR(255)          NULL,
    user_id    BIGINT                NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_at         datetime              NULL,
    updated_at         datetime              NULL,
    deleted            BIT(1)                NOT NULL,
    username           VARCHAR(255)          NOT NULL,
    password           VARCHAR(255)          NULL,
    email              VARCHAR(255)          NULL,
    phone              VARCHAR(255)          NULL,
    first_name         VARCHAR(255)          NULL,
    last_name          VARCHAR(255)          NULL,
    birthday           datetime              NULL,
    primary_address_id BIGINT                NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_phone UNIQUE (phone);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_PRIMARYADDRESS FOREIGN KEY (primary_address_id) REFERENCES address (id);