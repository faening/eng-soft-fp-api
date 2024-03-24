CREATE TABLE IF NOT EXISTS department (
    id_department int(20) NOT NULL AUTO_INCREMENT,
    description varchar(40) NOT NULL,
    status int NOT NULL DEFAULT 1,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_department)
);