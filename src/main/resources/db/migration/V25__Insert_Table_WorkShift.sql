INSERT INTO work_shift
    (description, start_of_workday, start_of_break, end_of_break, end_of_workday, reduced_shift, night_shift_allowance)
VALUES
    -- 8hrs
    ('Turno A (8H)', '06:00:00', '10:00:00', '12:00:00', '16:00:00', 0, 0),
    ('Turno B (8H)', '08:00:00', '12:00:00', '14:00:00', '18:00:00', 0, 0),
    ('Turno C (8H)', '18:00:00', '22:00:00', '00:00:00', '04:00:00', 0, 1),
    ('Turno D (8H)', '22:00:00', '02:00:00', '04:00:00', '08:00:00', 0, 1),

    -- 6hrs
    ('Turno E (6H)', '08:00:00', '12:00:00', '14:00:00', '16:00:00', 1, 0),
    ('Turno F (6H)', '10:00:00', '12:00:00', '14:00:00', '18:00:00', 1, 0),
    ('Turno G (6H)', '10:00:00', '14:00:00', '16:00:00', '18:00:00', 1, 0);