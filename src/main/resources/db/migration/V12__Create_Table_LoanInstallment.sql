CREATE TABLE IF NOT EXISTS loan_installment (
    id_loan_installment INT NOT NULL AUTO_INCREMENT,
    loan_id INT NOT NULL,
    installment_number INT NOT NULL,
    installment_value DECIMAL(10,2) NOT NULL,
    discount_month ENUM(
        'JANUARY',
        'FEBRUARY',
        'MARCH',
        'APRIL',
        'MAY',
        'JUNE',
        'JULY',
        'AUGUST',
        'SEPTEMBER',
        'OCTOBER',
        'NOVEMBER',
        'DECEMBER') NOT NULL,
    payment_status ENUM(
        'PENDING',
        'RELEASED',
        'APPROVED',
        'DENIED',
        'PAID',
        'CANCELED') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_loan_installment),
    FOREIGN KEY (loan_id) REFERENCES loan(id_loan)
);