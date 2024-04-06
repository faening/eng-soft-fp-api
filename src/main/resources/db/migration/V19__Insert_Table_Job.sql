INSERT INTO job
    (description, experience_level, base_salary, dangerousness, unhealthiness, department_id)
VALUES
    -- Administrativo
    ('Assistente Administrativo', 'TRAINEE', 1100.00, 0, 0, 1),
    ('Assistente Administrativo', 'ENTRY_LEVEL', 1500.00, 0, 0, 1),
    ('Assistente Administrativo', 'JUNIOR', 2000.00, 0, 0, 1),
    ('Assistente Administrativo', 'MID_LEVEL', 2600.00, 0, 0, 1),
    ('Assistente Administrativo', 'SENIOR', 3300.00, 0, 0, 1),
    ('Assistente Administrativo', 'EXPERT', 4100.00, 0, 0, 1),

    ('Chief Executive Officer (CEO)', 'EXPERT', 6100.00, 0, 0, 1),

    -- Financeiro
    ('Analista Financeiro', 'TRAINEE', 1100.00, 0, 0, 2),
    ('Analista Financeiro', 'ENTRY_LEVEL', 1500.00, 0, 0, 2),
    ('Analista Financeiro', 'JUNIOR', 2000.00, 0, 0, 2),
    ('Analista Financeiro', 'MID_LEVEL', 2600.00, 0, 0, 2),
    ('Analista Financeiro', 'SENIOR', 3300.00, 0, 0, 2),
    ('Analista Financeiro', 'EXPERT', 4100.00, 0, 0, 2),

    ('Contador', 'MID_LEVEL', 3000.00, 0, 0, 2),
    ('Contador', 'SENIOR', 3700.00, 0, 0, 2),
    ('Contador', 'EXPERT', 4500.00, 0, 0, 2),

    ('Chief Financial Officer (CFO)', 'EXPERT', 5900.00, 0, 0, 2),

    -- Recursos Humanos
    ('Assistente de Recursos Humanos', 'TRAINEE', 1100.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'ENTRY_LEVEL', 1400.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'JUNIOR', 1900.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'MID_LEVEL', 2500.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'SENIOR', 3200.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'EXPERT', 4000.00, 0, 0, 3),

    ('Chief Human Resources Officer (CHRO)', 'EXPERT', 5900.00, 0, 0, 3),

    -- Pesquisa e Desenvolvimento
    ('Engenheiro de Software', 'TRAINEE', 1100.00, 0, 0, 4),
    ('Engenheiro de Software', 'ENTRY_LEVEL', 1400.00, 0, 0, 4),
    ('Engenheiro de Software', 'JUNIOR', 1800.00, 0, 0, 4),
    ('Engenheiro de Software', 'MID_LEVEL', 2300.00, 0, 0, 4),
    ('Engenheiro de Software', 'SENIOR', 2900.00, 0, 0, 4),
    ('Engenheiro de Software', 'EXPERT', 3600.00, 0, 0, 4),

    ('Administrador de Banco de Dados (DBA)', 'MID_LEVEL', 2700.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'SENIOR', 3300.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'EXPERT', 4000.00, 0, 0, 4),

    ('Arquiteto de Software', 'MID_LEVEL', 2750.00, 0, 0, 4),
    ('Arquiteto de Software', 'SENIOR', 3350.00, 0, 0, 4),
    ('Arquiteto de Software', 'EXPERT', 4050.00, 0, 0, 4),

    ('Chief Technological Officer (CTO)', 'EXPERT', 5800.00, 0, 0, 1),

    -- Infraestrutura de TI
    ('Analista de Redes', 'TRAINEE', 1100.00, 0, 0, 5),
    ('Analista de Redes', 'ENTRY_LEVEL', 1400.00, 0, 0, 5),
    ('Analista de Redes', 'JUNIOR', 1600.00, 0, 0, 5),
    ('Analista de Redes', 'MID_LEVEL', 1900.00, 0, 0, 5),
    ('Analista de Redes', 'SENIOR', 2300.00, 0, 0, 5),
    ('Analista de Redes', 'EXPERT', 2700.00, 0, 0, 5),

    -- Marketing
    ('Analista de Marketing', 'TRAINEE', 1100.00, 0, 0, 6),
    ('Analista de Marketing', 'ENTRY_LEVEL', 1400.00, 0, 0, 6),
    ('Analista de Marketing', 'JUNIOR', 1700.00, 0, 0, 6),
    ('Analista de Marketing', 'MID_LEVEL', 2100.00, 0, 0, 6),
    ('Analista de Marketing', 'SENIOR', 2600.00, 0, 0, 6),
    ('Analista de Marketing', 'EXPERT', 3200.00, 0, 0, 6),

    ('Chief Marketing Officer (CMO)', 'EXPERT', 5000.00, 0, 0, 2),

    -- Vendas
    ('Representante de Vendas', 'TRAINEE', 1100.00, 0, 0, 7),
    ('Representante de Vendas', 'ENTRY_LEVEL', 1650.00, 0, 0, 7),
    ('Representante de Vendas', 'JUNIOR', 2050.00, 0, 0, 7),
    ('Representante de Vendas', 'MID_LEVEL', 2550.00, 0, 0, 7),
    ('Representante de Vendas', 'SENIOR', 3150.00, 0, 0, 7),
    ('Representante de Vendas', 'EXPERT', 3850.00, 0, 0, 7),

    -- SAC
    ('Analista de Suporte', 'ENTRY_LEVEL', 1100.00, 0, 0, 8),
    ('Analista de Suporte', 'ENTRY_LEVEL', 1400.00, 0, 0, 8),
    ('Analista de Suporte', 'JUNIOR', 1900.00, 0, 0, 8),
    ('Analista de Suporte', 'MID_LEVEL', 2250.00, 0, 0, 8),
    ('Analista de Suporte', 'SENIOR', 2650.00, 0, 0, 8),
    ('Analista de Suporte', 'EXPERT', 3150.00, 0, 0, 8),

    -- Produção
    ('Técnido de Produção', 'TRAINEE', 1100.00, 0, 2, 9),
    ('Técnido de Produção', 'ENTRY_LEVEL', 1400.00, 0, 2, 9),
    ('Técnido de Produção', 'JUNIOR', 1750.00, 0, 2, 9),
    ('Técnido de Produção', 'MID_LEVEL', 2200.00, 0, 2, 9),
    ('Técnido de Produção', 'SENIOR', 2750.00, 0, 2, 9),
    ('Técnido de Produção', 'EXPERT', 3400.00, 0, 2, 9),

    ('Operador de Máquinas', 'MID_LEVEL', 2600.00, 1, 3, 9),
    ('Operador de Máquinas', 'SENIOR', 3250.00, 1, 3, 9),
    ('Operador de Máquinas', 'EXPERT', 4000.00, 1, 3, 9),

    ('Técnido em Manutenção de Máquinas', 'MID_LEVEL', 2500.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas', 'SENIOR', 3150.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas', 'EXPERT', 3900.00, 1, 0, 9),

    ('Supervisor de Produção', 'MID_LEVEL', 3100.00, 0, 1, 9),
    ('Supervisor de Produção', 'SENIOR', 3850.00, 0, 1, 9),
    ('Supervisor de Produção', 'EXPERT', 4700.00, 0, 1, 9),

    ('Chief Operational Officer (COO)', 'EXPERT', 5200.00, 0, 0, 9),

    -- Qualidade
    ('Analista de Qualidade', 'TRAINEE', 1100.00, 0, 1, 10),
    ('Analista de Qualidade', 'ENTRY_LEVEL', 1400.00, 0, 1, 10),
    ('Analista de Qualidade', 'JUNIOR', 1750.00, 0, 1, 10),
    ('Analista de Qualidade', 'MID_LEVEL', 2200.00, 0, 1, 10),
    ('Analista de Qualidade', 'SENIOR', 2750.00, 0, 1, 10),
    ('Analista de Qualidade', 'EXPERT', 3400.00, 0, 1, 10),

    -- Jurídico
    ('Advogado', 'MID_LEVEL', 2800.00, 0, 0, 11),
    ('Advogado', 'SENIOR', 3300.00, 0, 0, 11),
    ('Advogado', 'EXPERT', 3900.00, 0, 0, 11),

    -- Limpeza
    ('Auxiliar de Limpeza', 'ENTRY_LEVEL', 1400.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'JUNIOR', 1700.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'MID_LEVEL', 2100.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'SENIOR', 2500.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'EXPERT', 3100.00, 0, 1, 12);