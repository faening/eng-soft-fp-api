CREATE TABLE IF NOT EXISTS payroll_item (
    id_payroll_item INT NOT NULL AUTO_INCREMENT,
    payroll_id INT NOT NULL,
    rubric_id INT NOT NULL,
    tax_or_value_id INT,
    financial_event_id INT,
    base_value DECIMAL(10, 2) NOT NULL,
    calculated_value DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_payroll_item),
    FOREIGN KEY (payroll_id) REFERENCES payroll(id_payroll),
    FOREIGN KEY (rubric_id) REFERENCES rubric(id_rubric),
    FOREIGN KEY (tax_or_value_id) REFERENCES tax_or_value(id_tax_or_value),
    FOREIGN KEY (financial_event_id) REFERENCES financial_event(id_financial_event)
);