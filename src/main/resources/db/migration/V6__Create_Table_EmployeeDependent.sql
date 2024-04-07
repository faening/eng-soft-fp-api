CREATE TABLE IF NOT EXISTS employee_dependent (
    id_employee_dependent INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    name VARCHAR(120) NOT NULL,
    rg VARCHAR(11) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    birth_date DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    special_needs BOOLEAN NOT NULL DEFAULT FALSE,
    family_allowance BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_employee_dependent),
    FOREIGN KEY (employee_id) REFERENCES employee(id_employee)
);