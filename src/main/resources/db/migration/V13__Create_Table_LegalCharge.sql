CREATE TABLE IF NOT EXISTS legal_charge (
    id_legal_charge INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    legal_charge_amount DECIMAL(10,2) NOT NULL,
    percentage  DECIMAL(5,2),
    release_date DATE NOT NULL,
    is_recurring BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_legal_charge),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);