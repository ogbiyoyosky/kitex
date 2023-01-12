DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS users;


CREATE table profiles (
      id INTEGER PRIMARY KEY AUTO_INCREMENT,
      contact_phone VARCHAR(256) NOT NULL,
      address VARCHAR(256) NOT NULL,
      created_at TIMESTAMP NOT NULL DEFAULT NOW(),
      updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
      deleted_at TIMESTAMP
);

CREATE TABLE users
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256),
    email VARCHAR(256),
    role VARCHAR(256),
    profile_id INTEGER DEFAULT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP
);


