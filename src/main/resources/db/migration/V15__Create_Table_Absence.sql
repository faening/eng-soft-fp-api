CREATE TABLE IF NOT EXISTS absence (
    id_absence INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    type ENUM('SICK_LEAVE', 'VACATION', 'DAY_OFF', 'MATERNITY_LEAVE', 'PATERNITY_LEAVE', 'UNPAID_LEAVE') NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    observation VARCHAR(255),
    status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_absence),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);