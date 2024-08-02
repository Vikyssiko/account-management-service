CREATE TABLE roles
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id              BIGSERIAL PRIMARY KEY,
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    role_id         BIGINT NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE accounts
(
    id              BIGSERIAL PRIMARY KEY,
    number          VARCHAR(255) NOT NULL,
    active          BOOLEAN DEFAULT TRUE,
    balance         DECIMAL NOT NULL,
    user_id         BIGINT NOT NULL,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users (id)
);