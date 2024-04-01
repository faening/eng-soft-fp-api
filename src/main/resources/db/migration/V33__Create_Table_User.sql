CREATE TABLE IF NOT EXISTS user (
    id_user INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(40) NOT NULL,
    full_name VARCHAR(120) DEFAULT NULL,
    password VARCHAR(255) NOT NULL,
    account_non_expired BOOLEAN DEFAULT FALSE,
    account_non_locked BOOLEAN DEFAULT FALSE,
    credentials_non_expired BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id_user),
    UNIQUE KEY (user_name)
);