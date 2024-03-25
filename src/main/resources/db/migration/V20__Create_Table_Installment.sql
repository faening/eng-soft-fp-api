CREATE TABLE IF NOT EXISTS installment (
    id_installment INT NOT NULL AUTO_INCREMENT,
    financial_event_id INT NOT NULL,
    number INT NOT NULL,
    value DOUBLE NOT NULL,
    paid TINYINT(1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_installment),
    FOREIGN KEY (financial_event_id) REFERENCES financial_event(id_financial_event)
);