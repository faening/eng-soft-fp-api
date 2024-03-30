INSERT INTO tax_or_value
    (type, range_id, range_minimum_wage, range_maximum_wage, fixed_value, tax_percentage, description)
VALUES
    -- Salário Mínimo
    -- Fonte: https://www.gov.br/planalto/pt-br/acompanhe-o-planalto/noticias/2023/12/salario-minimo-de-2024-tera-ganho-real-e-crescera-3pp-alem-dos-3-85-da-inflacao
    ('MINIMUM_WAGE', NULL, NULL, NULL, 1412.0, NULL, 'Valor do salário mínimo vigente no Brasil atualmente'),

    -- Auxílio Créche / Babá
    ('DAYCARE_ALLOWANCE', NULL, NULL, NULL, 45.0, NULL, 'Valor diário do auxílio créche ou babá'),

    -- Vale Transporte
    ('TRANSPORT_ALLOWANCE', NULL, NULL, NULL, 5.0, NULL, 'Valor diário do vale transporte'),

    -- Vale Alimentação
    ('MEAL_ALLOWANCE', NULL, NULL, NULL, 25.0, NULL, 'Valor diário do vale alimentação ou refeição'),

    -- Comissão de Vendas
    ('MEAL_ALLOWANCE', 1, 5000.0, 20000.0, NULL, 2.0, 'Percentual de comissão para vendas entre R$ 5.000.00 até R$ 20.000,00'),
    ('MEAL_ALLOWANCE', 2, 20000.01, 40000.0, NULL, 3.0, 'Percentual de comissão para vendas entre R$ 20.000,01 até R$ 40.000,00'),
    ('MEAL_ALLOWANCE', 3, 40000.01, 60000.0, NULL, 4.0, 'Percentual de comissão para vendas entre R$ 40.000,01 até R$ 60.000,00'),
    ('MEAL_ALLOWANCE', 4, 60000.01, 80000.0, NULL, 5.0, 'Percentual de comissão para vendas entre R$ 60.000,01 até R$ 80.000,00'),
    ('MEAL_ALLOWANCE', 5, 80000.01, 100000.0, NULL, 6.0, 'Percentual de comissão para vendas entre R$ 80.000,01 até R$ 100.000,00'),
    ('MEAL_ALLOWANCE', 6, 100000.01, 999999.99, NULL, 7.0, 'Percentual de comissão para vendas acima de R$ 100.000,00'),

    -- Adicional Noturno
    ('NIGHT_SHIFT_ALLOWANCE', NULL, NULL, NULL, NULL, 20.0, 'Percentual de adicional noturno'),

    -- Insalubridade
    ('NIGHT_SHIFT_ALLOWANCE', 1, NULL, NULL, NULL, 10.0, 'Percentual de adicional por insalubridade - grau mínimo'),
    ('NIGHT_SHIFT_ALLOWANCE', 2, NULL, NULL, NULL, 20.0, 'Percentual de adicional por insalubridade - grau médio'),
    ('NIGHT_SHIFT_ALLOWANCE', 3, NULL, NULL, NULL, 30.0, 'Percentual de adicional por insalubridade - grau máximo'),

    -- Periculosidade
    ('NIGHT_SHIFT_ALLOWANCE', NULL, NULL, NULL, NULL, 30.0, 'Percentual de adicional por periculosidade'),

    -- Salário Família
    ('FAMILY_ALLOWANCE', NULL, NULL, NULL, 70.6, 5.0, 'Percentual de adicional para salário família'),

    -- Tempo de Serviço
    ('TIME_SERVICE_ALLOWANCE', NULL, NULL, NULL, NULL, 5.0, 'Percentual de adicional para tempo de serviço'),

    -- INSS
    ('INSS', 1, 0.0, 1751.81, NULL, 8.0, 'Percentual de INSS para salário bruto entre R$ 0.00 até R$ 1.751,81'),
    ('INSS', 2, 1751.82, 2919.72, NULL, 9.0, 'Percentual de INSS para salário bruto entre R$ 1.751,82 até R$ 2.219,72'),
    ('INSS', 3, 2919.73, 5839.45, NULL, 11.0, 'Percentual de INSS para salário bruto entre R$ 2.219,73 até R$ 5.839,45'),
    ('INSS', 4, 5839.46, 999999.99, 642.34, NULL, 'Percentual de INSS para salário bruto acima de R$ 5.839,46'),

    -- IRRF
    ('IRRF', 1, 0.0, 1903.98, NULL, 0.0, 'Isenção de IRRF para salário bruto entre R$ 0.00 até R$ 1.903,98'),
    ('IRRF', 2, 1903.99, 2826.65, NULL, 7.5, 'Percentual de IRRF para salário bruto entre R$ 1.903,99 até R$ 2.826,65'),
    ('IRRF', 3, 2826.66, 3751.05, NULL, 15.0, 'Percentual de IRRF para salário bruto entre R$ 2.826,66 até R$ 3.751,05'),
    ('IRRF', 4, 3751.06, 4664.68, NULL, 22.5, 'Percentual de IRRF para salário bruto entre R$ 3.751,06 até R$ 4.664,68'),
    ('IRRF', 5, 4664.69, 999999.99, NULL, 27.5, 'Percentual de IRRF para salário bruto acima de R$ 4.664,69'),

    -- FGTS
    ('FGTS', NULL, NULL, NULL, NULL, 8.0, 'Percentual de FGTS recolhido sobre o salário bruto e demais remunerações'),

    -- Desconto de Vale Transporte
    ('TRANSPORT_DISCOUNT', NULL, NULL, NULL, NULL, 6.0, 'Percentual de desconto do vale transporte sobre o salário bruto'),

    -- Desconto de Vale Alimentação
    ('MEAL_DISCOUNT', NULL, NULL, NULL, NULL, 15.0, 'Percentual de desconto do vale alimentação sobre o salário bruto');