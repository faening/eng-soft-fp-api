CREATE TABLE IF NOT EXISTS hours_worked_sheet (
    id_hours_worked_sheet INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    regular_hours TIME,
    negative_hours TIME,
    overtime50 TIME,
    overtime100 TIME,
    time_bank TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_hours_worked_sheet),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);