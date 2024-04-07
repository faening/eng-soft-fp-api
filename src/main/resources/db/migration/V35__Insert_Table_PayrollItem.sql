INSERT INTO payroll_item
    (payroll_id, rubric_id, tax_or_value_id, base_value, calculated_value)
VALUES
    -- Employee 1: Hugo Osvaldo Pereira
    (1, 1, NULL, NULL, 9000.0),      -- Salário Base
    (1, 4, NULL, NULL, 1000.0),      -- Horas Extras
    (1, 43, NULL, NULL, 300.0),      -- Ressarcimento viagens
    (1, 80, 22, 10300.00, 624.34),   -- INSS
    (1, 81, 27, 9675.66, 2660.80);   -- IRRF