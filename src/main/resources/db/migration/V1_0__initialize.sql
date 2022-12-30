DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256),
    email VARCHAR(256),
    role VARCHAR(256),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP
);
