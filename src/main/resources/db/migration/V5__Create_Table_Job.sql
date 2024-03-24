CREATE TABLE IF NOT EXISTS job (
    id_job INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    experience_level ENUM('ENTRY_LEVEL', 'JUNIOR', 'MID_LEVEL', 'SENIOR', 'EXPERT') NOT NULL,
    base_salary DOUBLE NOT NULL DEFAULT 1400.0,  -- Salário mínimo aproximado no Brasil
    dangerousness INT NOT NULL DEFAULT 0,
    unhealthiness INT NOT NULL DEFAULT 0,
    department_id INT NOT NULL,
    status INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_job),
    FOREIGN KEY (department_id) REFERENCES department(id_department)
);