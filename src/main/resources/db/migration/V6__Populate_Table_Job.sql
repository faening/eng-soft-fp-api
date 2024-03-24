INSERT INTO job
    (description, experience_level, base_salary, dangerousness, unhealthiness, department_id)
VALUES
    -- Administrativo
    ('Gerente Administrativo', 'ENTRY_LEVEL', 2100.00, 0, 0, 1),
    ('Gerente Administrativo', 'JUNIOR', 2600.00, 0, 0, 1),
    ('Gerente Administrativo', 'MID_LEVEL', 3200.00, 0, 0, 1),
    ('Gerente Administrativo', 'SENIOR', 3900.00, 0, 0, 1),
    ('Gerente Administrativo', 'EXPERT', 4700.00, 0, 0, 1),

    ('Coordenador de Serviços Administrativos', 'ENTRY_LEVEL', 1700.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'JUNIOR', 2100.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'MID_LEVEL', 2600.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'SENIOR', 3200.00, 0, 0, 1),
    ('Coordenador de Serviços Administrativos', 'EXPERT', 3900.00, 0, 0, 1),

    ('Assistente Administrativo', 'ENTRY_LEVEL', 1400.00, 0, 0, 1),
    ('Assistente Administrativo', 'JUNIOR', 1700.00, 0, 0, 1),
    ('Assistente Administrativo', 'MID_LEVEL', 2100.00, 0, 0, 1),
    ('Assistente Administrativo', 'SENIOR', 2600.00, 0, 0, 1),
    ('Assistente Administrativo', 'EXPERT', 3200.00, 0, 0, 1),

    -- Financeiro
    ('Gerente Financeiro', 'ENTRY_LEVEL', 2100.00, 0, 0, 2),
    ('Gerente Financeiro', 'JUNIOR', 2600.00, 0, 0, 2),
    ('Gerente Financeiro', 'MID_LEVEL', 3200.00, 0, 0, 2),
    ('Gerente Financeiro', 'SENIOR', 3900.00, 0, 0, 2),
    ('Gerente Financeiro', 'EXPERT', 4700.00, 0, 0, 2),

    ('Contador', 'ENTRY_LEVEL', 1700.00, 0, 0, 2),
    ('Contador', 'JUNIOR', 2100.00, 0, 0, 2),
    ('Contador', 'MID_LEVEL', 2600.00, 0, 0, 2),
    ('Contador', 'SENIOR', 3200.00, 0, 0, 2),
    ('Contador', 'EXPERT', 3900.00, 0, 0, 2),

    ('Analista Financeiro', 'ENTRY_LEVEL', 1400.00, 0, 0, 2),
    ('Analista Financeiro', 'JUNIOR', 1700.00, 0, 0, 2),
    ('Analista Financeiro', 'MID_LEVEL', 2100.00, 0, 0, 2),
    ('Analista Financeiro', 'SENIOR', 2600.00, 0, 0, 2),
    ('Analista Financeiro', 'EXPERT', 3200.00, 0, 0, 2),

    -- Recursos Humanos
    ('Gerente de Recursos Humanos', 'ENTRY_LEVEL', 2100.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'JUNIOR', 2600.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'MID_LEVEL', 3200.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'SENIOR', 3900.00, 0, 0, 3),
    ('Gerente de Recursos Humanos', 'EXPERT', 4700.00, 0, 0, 3),

    ('Assistente de Recursos Humanos', 'ENTRY_LEVEL', 1700.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'JUNIOR', 2100.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'MID_LEVEL', 2600.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'SENIOR', 3200.00, 0, 0, 3),
    ('Assistente de Recursos Humanos', 'EXPERT', 3900.00, 0, 0, 3),

    ('Analista de Recursos Humanos', 'ENTRY_LEVEL', 1400.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'JUNIOR', 1700.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'MID_LEVEL', 2100.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'SENIOR', 2600.00, 0, 0, 3),
    ('Analista de Recursos Humanos', 'EXPERT', 3200.00, 0, 0, 3),

    -- Pesquisa e Desenvolvimento
    ('Arquiteto de Software', 'ENTRY_LEVEL', 2100.00, 0, 0, 4),
    ('Arquiteto de Software', 'JUNIOR', 2600.00, 0, 0, 4),
    ('Arquiteto de Software', 'MID_LEVEL', 3200.00, 0, 0, 4),
    ('Arquiteto de Software', 'SENIOR', 3900.00, 0, 0, 4),
    ('Arquiteto de Software', 'EXPERT', 4700.00, 0, 0, 4),

    ('Administrador de Banco de Dados (DBA)', 'ENTRY_LEVEL', 1700.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'JUNIOR', 2100.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'MID_LEVEL', 2600.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'SENIOR', 3200.00, 0, 0, 4),
    ('Administrador de Banco de Dados (DBA)', 'EXPERT', 3900.00, 0, 0, 4),

    ('Engenheiro de Software', 'ENTRY_LEVEL', 1400.00, 0, 0, 4),
    ('Engenheiro de Software', 'JUNIOR', 1700.00, 0, 0, 4),
    ('Engenheiro de Software', 'MID_LEVEL', 2100.00, 0, 0, 4),
    ('Engenheiro de Software', 'SENIOR', 2600.00, 0, 0, 4),
    ('Engenheiro de Software', 'EXPERT', 3200.00, 0, 0, 4),

    -- Infraestrutura de TI
    ('Analista de Redes', 'ENTRY_LEVEL', 1400.00, 0, 0, 5),
    ('Analista de Redes', 'JUNIOR', 1700.00, 0, 0, 5),
    ('Analista de Redes', 'MID_LEVEL', 2100.00, 0, 0, 5),
    ('Analista de Redes', 'SENIOR', 2600.00, 0, 0, 5),
    ('Analista de Redes', 'EXPERT', 3200.00, 0, 0, 5),

    -- Marketing
    ('Coordenador de Eventos e Promoções', 'ENTRY_LEVEL', 1400.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'JUNIOR', 1700.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'MID_LEVEL', 2100.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'SENIOR', 2600.00, 0, 0, 6),
    ('Coordenador de Eventos e Promoções', 'EXPERT', 3200.00, 0, 0, 6),

    ('Analista de Marketing', 'ENTRY_LEVEL', 1400.00, 0, 0, 6),
    ('Analista de Marketing', 'JUNIOR', 1700.00, 0, 0, 6),
    ('Analista de Marketing', 'MID_LEVEL', 2100.00, 0, 0, 6),
    ('Analista de Marketing', 'SENIOR', 2600.00, 0, 0, 6),
    ('Analista de Marketing', 'EXPERT', 3200.00, 0, 0, 6),

    -- Vendas
    ('Representante de Vendas', 'ENTRY_LEVEL', 1700.00, 0, 0, 7),
    ('Representante de Vendas', 'JUNIOR', 2100.00, 0, 0, 7),
    ('Representante de Vendas', 'MID_LEVEL', 2600.00, 0, 0, 7),
    ('Representante de Vendas', 'SENIOR', 3200.00, 0, 0, 7),
    ('Representante de Vendas', 'EXPERT', 3900.00, 0, 0, 7),

    -- SAC
    ('Analista de Suporte', 'ENTRY_LEVEL', 1700.00, 0, 0, 8),
    ('Analista de Suporte', 'JUNIOR', 2100.00, 0, 0, 8),
    ('Analista de Suporte', 'MID_LEVEL', 2600.00, 0, 0, 8),
    ('Analista de Suporte', 'SENIOR', 3200.00, 0, 0, 8),
    ('Analista de Suporte', 'EXPERT', 3900.00, 0, 0, 8),

    ('Atendente de Suporte', 'ENTRY_LEVEL', 1400.00, 0, 0, 8),
    ('Atendente de Suporte', 'JUNIOR', 1700.00, 0, 0, 8),
    ('Atendente de Suporte', 'MID_LEVEL', 2100.00, 0, 0, 8),
    ('Atendente de Suporte', 'SENIOR', 2600.00, 0, 0, 8),
    ('Atendente de Suporte', 'EXPERT', 3200.00, 0, 0, 8),

    -- Produção
    ('Gerente de Produção', 'ENTRY_LEVEL', 2100.00, 0, 0, 9),
    ('Gerente de Produção', 'JUNIOR', 2600.00, 0, 0, 9),
    ('Gerente de Produção', 'MID_LEVEL', 3200.00, 0, 0, 9),
    ('Gerente de Produção', 'SENIOR', 3900.00, 0, 0, 9),
    ('Gerente de Produção', 'EXPERT', 4700.00, 0, 0, 9),

    ('Supervisor de Produção', 'ENTRY_LEVEL', 1700.00, 0, 1, 9),
    ('Supervisor de Produção', 'JUNIOR', 2100.00, 0, 1, 9),
    ('Supervisor de Produção', 'MID_LEVEL', 2600.00, 0, 1, 9),
    ('Supervisor de Produção', 'SENIOR', 3200.00, 0, 1, 9),
    ('Supervisor de Produção', 'EXPERT', 3900.00, 0, 1, 9),

    ('Operador de Máquinas', 'ENTRY_LEVEL', 1400.00, 1, 1, 9),
    ('Operador de Máquinas', 'JUNIOR', 1700.00, 1, 1, 9),
    ('Operador de Máquinas', 'MID_LEVEL', 2100.00, 1, 1, 9),
    ('Operador de Máquinas', 'SENIOR', 2600.00, 1, 1, 9),
    ('Operador de Máquinas', 'EXPERT', 3200.00, 1, 1, 9),

    ('Técnido de Produção', 'ENTRY_LEVEL', 1400.00, 0, 1, 9),
    ('Técnido de Produção', 'JUNIOR', 1700.00, 0, 1, 9),
    ('Técnido de Produção', 'MID_LEVEL', 2100.00, 0, 1, 9),
    ('Técnido de Produção', 'SENIOR', 2600.00, 0, 1, 9),
    ('Técnido de Produção', 'EXPERT', 3200.00, 0, 1, 9),

    ('Técnido em Manutenção de Máquinas Industriais', 'ENTRY_LEVEL', 1400.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas Industriais', 'JUNIOR', 1700.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas Industriais', 'MID_LEVEL', 2100.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas Industriais', 'SENIOR', 2600.00, 1, 0, 9),
    ('Técnido em Manutenção de Máquinas Industriais', 'EXPERT', 3200.00, 1, 0, 9),

    -- Qualidade
    ('Analista de Qualidade', 'ENTRY_LEVEL', 1400.00, 0, 0, 10),
    ('Analista de Qualidade', 'JUNIOR', 1700.00, 0, 0, 10),
    ('Analista de Qualidade', 'MID_LEVEL', 2100.00, 0, 0, 10),
    ('Analista de Qualidade', 'SENIOR', 2600.00, 0, 0, 10),
    ('Analista de Qualidade', 'EXPERT', 3200.00, 0, 0, 10),

    -- Jurídico
    ('Advogado', 'ENTRY_LEVEL', 2100.00, 0, 0, 11),
    ('Advogado', 'JUNIOR', 2600.00, 0, 0, 11),
    ('Advogado', 'MID_LEVEL', 3200.00, 0, 0, 11),
    ('Advogado', 'SENIOR', 3900.00, 0, 0, 11),
    ('Advogado', 'EXPERT', 4700.00, 0, 0, 11),

    ('Estagiário de Direito', 'ENTRY_LEVEL', 1400.00, 0, 0, 11),
    ('Estagiário de Direito', 'JUNIOR', 1700.00, 0, 0, 11),
    ('Estagiário de Direito', 'MID_LEVEL', 2100.00, 0, 0, 11),
    ('Estagiário de Direito', 'SENIOR', 2600.00, 0, 0, 11),
    ('Estagiário de Direito', 'EXPERT', 3200.00, 0, 0, 11),

    -- Limpeza
    ('Auxiliar de Limpeza', 'ENTRY_LEVEL', 1400.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'JUNIOR', 1700.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'MID_LEVEL', 2100.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'SENIOR', 2600.00, 0, 1, 12),
    ('Auxiliar de Limpeza', 'EXPERT', 3200.00, 0, 1, 12);