CREATE TABLE IF NOT EXISTS user (
    id_user INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(40) DEFAULT NULL,
    fullname VARCHAR(255) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    account_non_expired BIT(1) DEFAULT NULL,
    account_non_locked BIT(1) DEFAULT NULL,
    credentials_non_expired BIT(1) DEFAULT NULL,
    enabled BIT(1) DEFAULT NULL,
    PRIMARY KEY (id_user),
    UNIQUE KEY (username)
);