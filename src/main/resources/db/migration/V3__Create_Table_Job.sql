CREATE TABLE IF NOT EXISTS job (
    id_job INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    experience_level ENUM('TRAINEE', 'ENTRY_LEVEL', 'JUNIOR', 'MID_LEVEL', 'SENIOR', 'EXPERT') NOT NULL DEFAULT 'ENTRY_LEVEL',
    base_salary DOUBLE NOT NULL DEFAULT 1400.0,  -- Salário mínimo aproximado no Brasil
    dangerousness BOOLEAN NOT NULL DEFAULT FALSE,
    unhealthiness BOOLEAN NOT NULL DEFAULT FALSE,
    department_id INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_job),
    FOREIGN KEY (department_id) REFERENCES department(id_department)
);