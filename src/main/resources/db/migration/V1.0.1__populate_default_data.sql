insert into t_role(id, role_name, code_name) values(1, 'ADMIN_ROLE', 'SYS31072023');

insert into t_permission(id, role_id, name)
values
    (2, 1, 'ADMIN_ROLE.WRITE'),
    (3, 1, 'ADMIN_ROLE.READ'),
    (4, 1, 'ADMIN_ROLE.UPDATE'),
    (5, 1, 'ADMIN_ROLE.DELETE');

insert into t_user(id, username, password, user_type, last_name, first_name, corporate_type, role_id, email_address)
values(6, 'admin', '$2y$12$R2gix.Nr/E4j9pmKVQHA4u/x.oyWf/wEPUBcQwxYerQSwqQXMcWZO', 'USER_C', 'admin', 'admin', 'OTHERS', 1, 'admin@test.com');
