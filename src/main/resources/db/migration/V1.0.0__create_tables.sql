CREATE TABLE t_role(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    code_name VARCHAR(50) NOT NULL UNIQUE,
    role_description TEXT
);

CREATE TABLE t_user(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    username VARCHAR(50) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email_address VARCHAR(50) NOT NULL UNIQUE,
    user_type VARCHAR(50) NOT NULL,
    corporate_type VARCHAR(50) NOT NULL,
    role_id BIGINT REFERENCES t_role(id)
);

CREATE TABLE t_bank_branch(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    created_by_id BIGINT NOT NULL REFERENCES t_user(id),
    modified_by_id BIGINT REFERENCES t_user(id),
    branch_code VARCHAR(50) NOT NULL UNIQUE,
    branch_name VARCHAR(50) NOT NULL
);

CREATE TABLE t_institution(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    created_by_id BIGINT NOT NULL REFERENCES t_user(id),
    modified_by_id BIGINT REFERENCES t_user(id),
    institution_code VARCHAR(50) NOT NULL UNIQUE,
    institution_name VARCHAR(50) NOT NULL
);

CREATE TABLE t_user_bank_branch(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    created_by_id BIGINT NOT NULL REFERENCES t_user(id),
    modified_by_id BIGINT REFERENCES t_user(id),
    user_id BIGINT REFERENCES t_user(id),
    bank_branch_id BIGINT REFERENCES t_bank_branch(id)
);

CREATE TABLE t_permission(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    role_id BIGINT REFERENCES t_role(id),
    name VARCHAR(50) NOT NULL
);