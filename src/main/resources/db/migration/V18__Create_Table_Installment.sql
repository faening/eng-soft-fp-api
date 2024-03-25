CREATE TABLE IF NOT EXISTS installment (
    id_installment INT NOT NULL AUTO_INCREMENT,
    number INT NOT NULL,
    value DOUBLE NOT NULL,
    paid TINYINT(1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_installment)
);