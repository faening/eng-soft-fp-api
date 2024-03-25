CREATE TABLE IF NOT EXISTS financial_event (
    id_financial_event INT NOT NULL,
    employee_id INT NOT NULL,
    rubric_id INT NOT NULL,
    value DOUBLE NOT NULL,
    release_date DATE NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_financial_event),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee),
    FOREIGN KEY (rubric_id) REFERENCES rubric(id_rubric)
);