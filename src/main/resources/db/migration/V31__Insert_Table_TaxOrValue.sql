INSERT INTO tax_or_value
    (type, range_id, range_minimum_wage, range_maximum_wage, fixed_value, tax_percentage, description)
VALUES
    -- Salário Mínimo
    -- Fonte: https://www.gov.br/planalto/pt-br/acompanhe-o-planalto/noticias/2023/12/salario-minimo-de-2024-tera-ganho-real-e-crescera-3pp-alem-dos-3-85-da-inflacao
    ('MINIMUM_WAGE', NULL, NULL, NULL, 1412.00, NULL, 'Valor do salário mínimo vigente no Brasil atualmente'),

    -- Auxílio Créche / Babá
    ('DAYCARE_ALLOWANCE', NULL, NULL, NULL, 45.00, NULL, 'Valor diário do auxílio créche ou babá'),

    -- Vale Transporte
    ('TRANSPORT_ALLOWANCE', NULL, NULL, NULL, 5.00, NULL, 'Valor diário do vale transporte'),

    -- Vale Alimentação
    ('MEAL_ALLOWANCE', NULL, NULL, NULL, 25.00, NULL, 'Valor diário do vale alimentação ou refeição'),

    -- Comissão de Vendas
    ('SALES_ALLOWANCE', 1, 0.00, 5000.00, NULL, 1.00, 'Percentual de comissão para vendas abaixo de R$ 5.000.00'),
    ('SALES_ALLOWANCE', 2, 5000.01, 20000.00, NULL, 2.00, 'Percentual de comissão para vendas entre R$ 5.000.00 até R$ 20.000,00'),
    ('SALES_ALLOWANCE', 3, 20000.01, 40000.00, NULL, 3.00, 'Percentual de comissão para vendas entre R$ 20.000,01 até R$ 40.000,00'),
    ('SALES_ALLOWANCE', 4, 40000.01, 60000.00, NULL, 4.00, 'Percentual de comissão para vendas entre R$ 40.000,01 até R$ 60.000,00'),
    ('SALES_ALLOWANCE', 5, 60000.01, 80000.00, NULL, 5.00, 'Percentual de comissão para vendas entre R$ 60.000,01 até R$ 80.000,00'),
    ('SALES_ALLOWANCE', 6, 80000.01, 100000.00, NULL, 6.00, 'Percentual de comissão para vendas entre R$ 80.000,01 até R$ 100.000,00'),
    ('SALES_ALLOWANCE', 7, 100000.01, 999999.99, NULL, 7.00, 'Percentual de comissão para vendas acima de R$ 100.000,00'),

    -- Adicional Noturno
    ('NIGHT_SHIFT_ALLOWANCE', NULL, NULL, NULL, NULL, 20.00, 'Percentual de adicional noturno'),

    -- Insalubridade
    ('UNHEALTHINESS_ALLOWANCE', 1, NULL, NULL, NULL, 10.00, 'Percentual de adicional por insalubridade - grau mínimo'),
    ('UNHEALTHINESS_ALLOWANCE', 2, NULL, NULL, NULL, 20.00, 'Percentual de adicional por insalubridade - grau médio'),
    ('UNHEALTHINESS_ALLOWANCE', 3, NULL, NULL, NULL, 30.00, 'Percentual de adicional por insalubridade - grau máximo'),

    -- Periculosidade
    ('DANGEROUSNESS_ALLOWANCE', NULL, NULL, NULL, NULL, 30.00, 'Percentual de adicional por periculosidade'),

    -- Salário Família
    ('FAMILY_ALLOWANCE', NULL, NULL, NULL, 70.60, 5.00, 'Percentual de adicional para salário família'),

    -- Tempo de Serviço
    ('TIME_SERVICE_ALLOWANCE', NULL, NULL, NULL, NULL, 5.00, 'Percentual de adicional para tempo de serviço'),

    -- INSS
    ('INSS', 1, 0.00, 1751.81, NULL, 8.00, 'Percentual de INSS para salário bruto entre R$ 0.00 até R$ 1.751,81'),
    ('INSS', 2, 1751.82, 2919.72, NULL, 9.00, 'Percentual de INSS para salário bruto entre R$ 1.751,82 até R$ 2.219,72'),
    ('INSS', 3, 2919.73, 5839.45, NULL, 11.00, 'Percentual de INSS para salário bruto entre R$ 2.219,73 até R$ 5.839,45'),
    ('INSS', 4, 5839.46, 999999.99, 642.34, NULL, 'Percentual de INSS para salário bruto acima de R$ 5.839,46'),

    -- IRRF
    ('IRRF', 1, 0.00, 1903.98, NULL, 0.00, 'Isenção de IRRF para salário bruto entre R$ 0.00 até R$ 1.903,98'),
    ('IRRF', 2, 1903.99, 2826.65, NULL, 7.50, 'Percentual de IRRF para salário bruto entre R$ 1.903,99 até R$ 2.826,65'),
    ('IRRF', 3, 2826.66, 3751.05, NULL, 15.00, 'Percentual de IRRF para salário bruto entre R$ 2.826,66 até R$ 3.751,05'),
    ('IRRF', 4, 3751.06, 4664.68, NULL, 22.50, 'Percentual de IRRF para salário bruto entre R$ 3.751,06 até R$ 4.664,68'),
    ('IRRF', 5, 4664.69, 999999.99, NULL, 27.50, 'Percentual de IRRF para salário bruto acima de R$ 4.664,69'),

    -- FGTS
    ('FGTS', NULL, NULL, NULL, NULL, 8.00, 'Percentual de FGTS recolhido sobre o salário bruto e demais remunerações'),

    -- Desconto de Vale Transporte
    ('TRANSPORT_DISCOUNT', NULL, NULL, NULL, NULL, 6.00, 'Percentual de desconto do vale transporte sobre o salário bruto'),

    -- Desconto de Vale Alimentação
    ('MEAL_DISCOUNT', NULL, NULL, NULL, NULL, 15.00, 'Percentual de desconto do vale alimentação sobre o salário bruto');