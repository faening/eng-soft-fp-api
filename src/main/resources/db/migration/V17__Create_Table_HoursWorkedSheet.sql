CREATE TABLE IF NOT EXISTS hours_worked_sheet (
    id_hours_worked_sheet INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    normal_hours TIME,
    negative_hours TIME,
    extra_hours_50 TIME,
    extra_hours_100 TIME,
    hours_bank TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_hours_worked_sheet),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);