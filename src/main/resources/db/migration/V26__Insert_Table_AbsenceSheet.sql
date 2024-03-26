INSERT INTO absence_sheet
    (employee_id, type, start_date, end_date, observation, status)
VALUES
    -- Dia de Folga
    (37, 'MATERNITY_LEAVE', '2023-10-25', '2024-04-25', 'Z34', 'APPROVED'),

    -- Licença Maternidade
    (46, 'DAY_OFF', '2023-03-22', '2024-03-22', 'DAY OFF', 'APPROVED'),
    (20, 'DAY_OFF', '2023-03-11', '2024-03-11', 'DAY OFF', 'APPROVED'),

    -- Falta Não Justificada
    (24, 'ABSENCE_WITHOUT_JUSTIFICATION', '2024-03-05', '2024-03-05', 'NOT JUSTIFIED', 'APPROVED'),
    (48, 'ABSENCE_WITHOUT_JUSTIFICATION', '2024-03-15', '2024-03-15', 'NOT JUSTIFIED', 'APPROVED');


