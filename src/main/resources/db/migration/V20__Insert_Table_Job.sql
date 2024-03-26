INSERT INTO job
    (description, experience_level, base_salary, dangerousness, unhealthiness, department_id)
VALUES
    -- Administrativo
    ('Assistente Administrativo', 'TRAINEE', 1100.00, 0, 0, 1),
    ('Assistente Administrativo', 'ENTRY_LEVEL', 1500.00, 0, 0, 1),
    ('Assistente Administrativo', 'JUNIOR', 1900.00, 0, 0, 1),
    ('Assistente Administrativo', 'MID_LEVEL', 2400.00, 0, 0, 1),
    ('Assistente Administrativo', 'SENIOR', 2900.00, 0, 0, 1),
    ('Assistente Administrativo', 'EXPERT', 3500.00, 0, 0, 1),

    ('Coordenador de Serviços Administrativos', 'MID_LEVEL', 3500.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'SENIOR', 4200.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'EXPERT', 5000.00, 0, 0, 1),

    ('Gerente Administrativo', 'MID_LEVEL', 5000.00, 0, 0, 1),
    ('Gerente Administrativo', 'SENIOR', 5900.00, 0, 0, 1),
    ('Gerente Administrativo', 'EXPERT', 6900.00, 0, 0, 1),

    -- Financeiro
    ('Analista Financeiro', 'TRAINEE', 1100.00, 0, 0, 2),
    ('Analista Financeiro', 'ENTRY_LEVEL', 1500.00, 0, 0, 2),
    ('Analista Financeiro', 'JUNIOR', 1900.00, 0, 0, 2),
    ('Analista Financeiro', 'MID_LEVEL', 2400.00, 0, 0, 2),
    ('Analista Financeiro', 'SENIOR', 2900.00, 0, 0, 2),
    ('Analista Financeiro', 'EXPERT', 3500.00, 0, 0, 2),

    ('Contador', 'MID_LEVEL', 3500.00, 0, 0, 2),
    ('Contador', 'SENIOR', 4200.00, 0, 0, 2),
    ('Contador', 'EXPERT', 5000.00, 0, 0, 2),

    ('Gerente Financeiro', 'MID_LEVEL', 5000.00, 0, 0, 2),
    ('Gerente Financeiro', 'SENIOR', 5900.00, 0, 0, 2),
    ('Gerente Financeiro', 'EXPERT', 6900.00, 0, 0, 2),

    -- Recursos Humanos
    ('Analista de Recursos Humanos', 'TRAINEE', 1100.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'ENTRY_LEVEL', 1400.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'JUNIOR', 1800.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'MID_LEVEL', 2300.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'SENIOR', 2800.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'EXPERT', 3400.00, 0, 0, 3),

    ('Assistente de Recursos Humanos', 'MID_LEVEL', 3400.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'SENIOR', 4100.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'EXPERT', 4900.00, 0, 0, 3),

    ('Gerente de Recursos Humanos', 'MID_LEVEL', 4900.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'SENIOR', 5800.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'EXPERT', 6800.00, 0, 0, 3),

    -- Pesquisa e Desenvolvimento
    ('Engenheiro de Software', 'TRAINEE', 1100.00, 0, 0, 4),
    ('Engenheiro de Software', 'ENTRY_LEVEL', 1300.00, 0, 0, 4),
    ('Engenheiro de Software', 'JUNIOR', 1700.00, 0, 0, 4),
    ('Engenheiro de Software', 'MID_LEVEL', 2200.00, 0, 0, 4),
    ('Engenheiro de Software', 'SENIOR', 2700.00, 0, 0, 4),
    ('Engenheiro de Software', 'EXPERT', 3300.00, 0, 0, 4),

    ('Administrador de Banco de Dados (DBA)', 'MID_LEVEL', 3300.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'SENIOR', 4000.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'EXPERT', 4800.00, 0, 0, 4),

    ('Arquiteto de Software', 'MID_LEVEL', 3300.00, 0, 0, 4),
    ('Arquiteto de Software', 'SENIOR', 4000.00, 0, 0, 4),
    ('Arquiteto de Software', 'EXPERT', 4800.00, 0, 0, 4),

    -- Infraestrutura de TI
    ('Analista de Redes', 'TRAINEE', 1100.00, 0, 0, 5),
    ('Analista de Redes', 'ENTRY_LEVEL', 1300.00, 0, 0, 5),
    ('Analista de Redes', 'JUNIOR', 1700.00, 0, 0, 5),
    ('Analista de Redes', 'MID_LEVEL', 2200.00, 0, 0, 5),
    ('Analista de Redes', 'SENIOR', 2700.00, 0, 0, 5),
    ('Analista de Redes', 'EXPERT', 3300.00, 0, 0, 5),

    -- Marketing
    ('Analista de Marketing', 'TRAINEE', 1100.00, 0, 0, 6),
    ('Analista de Marketing', 'ENTRY_LEVEL', 1500.00, 0, 0, 6),
    ('Analista de Marketing', 'JUNIOR', 1900.00, 0, 0, 6),
    ('Analista de Marketing', 'MID_LEVEL', 2400.00, 0, 0, 6),
    ('Analista de Marketing', 'SENIOR', 2900.00, 0, 0, 6),
    ('Analista de Marketing', 'EXPERT', 3500.00, 0, 0, 6),

    ('Coordenador de Eventos e Promoções', 'MID_LEVEL', 3500.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'SENIOR', 4200.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'EXPERT', 5000.00, 0, 0, 6),

    -- Vendas
    ('Representante de Vendas', 'TRAINEE', 1100.00, 0, 0, 7),
    ('Representante de Vendas', 'ENTRY_LEVEL', 1500.00, 0, 0, 7),
    ('Representante de Vendas', 'JUNIOR', 1900.00, 0, 0, 7),
    ('Representante de Vendas', 'MID_LEVEL', 2800.00, 0, 0, 7),
    ('Representante de Vendas', 'SENIOR', 3400.00, 0, 0, 7),
    ('Representante de Vendas', 'EXPERT', 4100.00, 0, 0, 7),

    -- SAC
    ('Analista de Suporte', 'ENTRY_LEVEL', 1100.00, 0, 0, 8),
    ('Analista de Suporte', 'ENTRY_LEVEL', 1300.00, 0, 0, 8),
    ('Analista de Suporte', 'JUNIOR', 1700.00, 0, 0, 8),
    ('Analista de Suporte', 'MID_LEVEL', 2200.00, 0, 0, 8),
    ('Analista de Suporte', 'SENIOR', 2700.00, 0, 0, 8),
    ('Analista de Suporte', 'EXPERT', 3300.00, 0, 0, 8),

    -- Produção
    ('Técnido de Produção', 'TRAINEE', 1100.00, 0, 1, 9),
    ('Técnido de Produção', 'ENTRY_LEVEL', 1250.00, 0, 1, 9),
    ('Técnido de Produção', 'JUNIOR', 1650.00, 0, 1, 9),
    ('Técnido de Produção', 'MID_LEVEL', 2150.00, 0, 1, 9),
    ('Técnido de Produção', 'SENIOR', 2650.00, 0, 1, 9),
    ('Técnido de Produção', 'EXPERT', 3250.00, 0, 1, 9),

    ('Operador de Máquinas', 'MID_LEVEL', 2150.00, 1, 1, 9),
    ('Operador de Máquinas', 'SENIOR', 2650.00, 1, 1, 9),
    ('Operador de Máquinas', 'EXPERT', 3250.00, 1, 1, 9),

    ('Técnido em Manutenção de Máquinas', 'MID_LEVEL', 2250.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas', 'SENIOR', 2750.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas', 'EXPERT', 3350.00, 1, 0, 9),

    ('Supervisor de Produção', 'MID_LEVEL', 2350.00, 0, 1, 9),
    ('Supervisor de Produção', 'SENIOR', 2850.00, 0, 1, 9),
    ('Supervisor de Produção', 'EXPERT', 3450.00, 0, 1, 9),

    ('Gerente de Produção', 'MID_LEVEL', 3450.00, 0, 0, 9),
    ('Gerente de Produção', 'SENIOR', 3950.00, 0, 0, 9),
    ('Gerente de Produção', 'EXPERT', 4650.00, 0, 0, 9),

    -- Qualidade
    ('Analista de Qualidade', 'TRAINEE', 1100.00, 0, 1, 10),
    ('Analista de Qualidade', 'ENTRY_LEVEL', 1225.00, 0, 1, 10),
    ('Analista de Qualidade', 'JUNIOR', 1625.00, 0, 1, 10),
    ('Analista de Qualidade', 'MID_LEVEL', 2125.00, 0, 1, 10),
    ('Analista de Qualidade', 'SENIOR', 2625.00, 0, 1, 10),
    ('Analista de Qualidade', 'EXPERT', 3225.00, 0, 1, 10),

    -- Jurídico
    ('Advogado', 'MID_LEVEL', 3500.00, 0, 0, 11),
    ('Advogado', 'SENIOR', 4200.00, 0, 0, 11),
    ('Advogado', 'EXPERT', 5000.00, 0, 0, 11),

    -- Limpeza
    ('Auxiliar de Limpeza', 'ENTRY_LEVEL', 1300.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'JUNIOR', 1700.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'MID_LEVEL', 2200.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'SENIOR', 2700.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'EXPERT', 3300.00, 0, 1, 12);

