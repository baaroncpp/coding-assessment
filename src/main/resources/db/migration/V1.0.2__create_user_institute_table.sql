CREATE TABLE t_user_institution(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    created_by_id BIGINT NOT NULL REFERENCES t_user(id),
    modified_by_id BIGINT REFERENCES t_user(id),
    user_id BIGINT NOT NULL REFERENCES t_user(id),
    institution_id BIGINT NOT NULL REFERENCES t_institution(id)
);