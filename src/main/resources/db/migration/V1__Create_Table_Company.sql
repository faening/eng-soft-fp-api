CREATE TABLE IF NOT EXISTS company (
    id_company int(20) NOT NULL AUTO_INCREMENT,
    corporate_name varchar(100) NOT NULL,
    trade_name varchar(100) NOT NULL,
    cnpj varchar(14) NOT NULL,
    ie varchar(10) NOT NULL,
    opening_date date DEFAULT NULL,
    phone varchar(10) DEFAULT NULL,
    email varchar(50) DEFAULT NULL,
    address_street varchar(100) NOT NULL,
    address_number varchar(6) NOT NULL,
    address_complement varchar(100) DEFAULT NULL,
    address_city varchar(100) NOT NULL,
    address_uf enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') NOT NULL,
    address_zip_code varchar(8) NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_company)
);