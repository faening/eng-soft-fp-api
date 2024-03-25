CREATE TABLE IF NOT EXISTS work_shift (
    id_work_shift INT AUTO_INCREMENT,
    description VARCHAR(80) NOT NULL,
    start_of_workday TIME NOT NULL,
    start_of_break TIME NOT NULL,
    end_of_break TIME NOT NULL,
    end_of_workday TIME NOT NULL,
    night_shift_allowance BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_work_shift)
);