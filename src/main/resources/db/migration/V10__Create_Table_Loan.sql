CREATE TABLE IF NOT EXISTS loan (
    id_loan INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    loan_amount_value DECIMAL(10,2) NOT NULL,
    installment_quantity INT NOT NULL,
    request_date DATE NOT NULL,
    approval_date DATE,
    company_payment_date DATE,
    company_payment_status ENUM('PENDING', 'RELEASED', 'APPROVED', 'DENIED', 'PAID') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_loan),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);