CREATE TABLE IF NOT EXISTS company (
    id_company INT NOT NULL AUTO_INCREMENT,
    corporate_name VARCHAR(100) NOT NULL,
    trade_name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    ie VARCHAR(10) NOT NULL,
    opening_date DATE DEFAULT NULL,
    phone VARCHAR(10) DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    address_street VARCHAR(100) NOT NULL,
    address_number VARCHAR(6) NOT NULL,
    address_complement VARCHAR(100) DEFAULT NULL,
    address_city VARCHAR(100) NOT NULL,
    address_uf ENUM('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') NOT NULL,
    address_zip_code VARCHAR(8) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_company)
);