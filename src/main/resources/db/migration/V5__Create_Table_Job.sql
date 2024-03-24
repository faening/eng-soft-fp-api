CREATE TABLE IF NOT EXISTS job (
    id_job int(20) NOT NULL AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    experience_level enum('ENTRY_LEVEL', 'JUNIOR', 'MID_LEVEL', 'SENIOR', 'EXPERT') NOT NULL,
    base_salary DOUBLE NOT NULL DEFAULT 1400.0,  -- Salário mínimo aproximado no Brasil
    dangerousness INT NOT NULL DEFAULT 0,
    unhealthiness INT NOT NULL DEFAULT 0,
    department_id INT NOT NULL,
    status INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_job),
    FOREIGN KEY (department_id) REFERENCES department(id_department)
);