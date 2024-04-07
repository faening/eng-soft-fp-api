CREATE TABLE IF NOT EXISTS union_contribution (
    id_union_contribution INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    release_year YEAR NOT NULL,
    opted_out BOOLEAN NOT NULL DEFAULT FALSE,
    payment_status ENUM(
        'PENDING',
        'RELEASED',
        'APPROVED',
        'DENIED',
        'PAID',
        'CANCELED') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_union_contribution),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);