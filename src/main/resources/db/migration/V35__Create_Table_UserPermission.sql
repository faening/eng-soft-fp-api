CREATE TABLE IF NOT EXISTS user_permission (
    user_id INT NOT NULL,
    permission_id INT NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    KEY fk_user_permission_permission (permission_id),
    FOREIGN KEY (user_id) REFERENCES user(id_user),
    FOREIGN KEY (permission_id) REFERENCES permission(id_permission)
);