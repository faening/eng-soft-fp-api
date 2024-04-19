CREATE TABLE IF NOT EXISTS trip_expense (
    id_trip_expense INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status enum(
        'PENDING',
        'APPROVED',
        'REJECTED'
    ) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_trip_expense),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);