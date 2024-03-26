ALTER TABLE department
ADD COLUMN manager_id INT AFTER description,
ADD FOREIGN KEY (manager_id) REFERENCES employee(id_employee);