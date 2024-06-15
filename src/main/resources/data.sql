insert into security_users
    (name, surname, patronymic, email, phone, is_active, password)
values
('Иван', 'Иванов', 'Иванович', 'ivanov@ya.ru', '+7-800-55-55-535', true, '$2a$10$JtRcZrjN631R71Q41j6Tau0FwCyTZ7tJhW/.6p21ZP2KkXtD.GvZW');

insert into user_role (user_id, roles)
values (1, 'USER');

insert into calculations (is_current, object_id, user_id, customer, note)
values (true, null, 1, null, null);