CREATE TABLE IF NOT EXISTS rubric (
    id_rubric INT NOT NULL AUTO_INCREMENT,
    code INT NOT NULL UNIQUE,
    name VARCHAR(180) NOT NULL,
    kind VARCHAR(180) NOT NULL,
    description TEXT NOT NULL,
    type ENUM('INCOME', 'DEDUCTION', 'NEUTRAL') NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_rubric)
);