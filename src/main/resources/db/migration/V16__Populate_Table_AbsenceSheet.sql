INSERT INTO absence_sheet
    (employee_id, type, start_date, end_date, observation, status)
VALUES
    -- Atestado Médico
    (8, 'SICK_LEAVE', '2022-03-20 08:00:00', '2022-03-20 18:00:00', 'CID 10.45', 'APPROVED'),
    (17, 'SICK_LEAVE', '2022-03-25 08:00:00', '2022-03-25 18:00:00', 'CID 12.02', 'PENDING'),

    -- Licença Maternidade
    (38, 'MATERNITY_LEAVE', '2022-02-10 18:00:00', '2022-07-10 04:00:00', 'CID 21.05', 'APPROVED'),
    (23, 'MATERNITY_LEAVE', '2022-12-18 08:00:00', '2022-06-18 18:00:00', 'CID 21.05', 'APPROVED'),

    -- Falta Não Justificada
    (33, 'UNPAID_LEAVE', '2022-03-18 08:00:00', '2022-06-18 18:00:00', 'CID 47.03', 'APPROVED');