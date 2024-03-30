CREATE TABLE IF NOT EXISTS benefit_voucher (
    id_benefit_voucher INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    paid_value DOUBLE NOT NULL,
    release_date DATE NOT NULL,
    description VARCHAR(120) NOT NULL,
    benefit_type ENUM('FOOD', 'TRANSPORT') NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'DENIED') NOT NULL DEFAULT 'PENDING',
    payroll_deductible BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_benefit_voucher),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);