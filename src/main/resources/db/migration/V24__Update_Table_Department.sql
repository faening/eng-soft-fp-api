START TRANSACTION;

UPDATE department SET manager_id = 1 WHERE id_department IN (1, 7, 11, 12);
UPDATE department SET manager_id = 4 WHERE id_department = 2;
UPDATE department SET manager_id = 7 WHERE id_department = 3;
UPDATE department SET manager_id = 10 WHERE id_department IN (4, 5);
UPDATE department SET manager_id = 16 WHERE id_department IN (6, 8);
UPDATE department SET manager_id = 27 WHERE id_department IN (9, 10);

COMMIT;