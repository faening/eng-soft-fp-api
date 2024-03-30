CREATE TABLE IF NOT EXISTS financial_event (
    id_financial_event INT NOT NULL,
    employee_id INT NOT NULL,
    rubric_id INT NOT NULL,
    value DECIMAL(10,2) NOT NULL,
    release_date DATE NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'DENIED') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_financial_event),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee),
    FOREIGN KEY (rubric_id) REFERENCES rubric(id_rubric)
);