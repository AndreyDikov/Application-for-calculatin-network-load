insert into security_users
    (name, surname, patronymic, email, phone, is_active, password)
values
('Иван', 'Иванов', 'Иванович', 'ivanov@ya.ru', '+7-800-55-55-535', true, '$2a$10$JtRcZrjN631R71Q41j6Tau0FwCyTZ7tJhW/.6p21ZP2KkXtD.GvZW');

insert into user_role (user_id, roles)
values (1, 'USER');

insert into calculations (is_current, object_id, user_id, customer, note)
values (true, null, 1, null, null);

insert into objects (user_id, address, name, region, square)
values (1, 'ул. Гарибальде, д. 4, корп. 5, квартира 67', 'Московский объект', 'г.Москва', 150.0);

-- insert into calculations (is_current, object_id, user_id, customer, note)
-- values (false, 1, 1, 'ОАО "Мега Люль"', 'Тестовый расчет');
--
-- insert into sections (limit_value, calculation_id, name)
-- values (200.0, 2, 'Секция 1');
-- insert into consumers (number, section_id, name, type)
-- values (200, 1, 'Квартиры', 'FLATS_WITH_ELECTRIC_STOVE_10_kW');
-- insert into consumers (number, section_id, name, type)
-- values (50, 1, 'Коммерческие помещения', 'EMBEDDED_PREMISES_BKT_AREA_MORE_THAN_100_m2');
-- insert into consumers (number, section_id, name, type)
-- values (50, 1, 'Коммерческие помещения', 'EMBEDDED_PREMISES_BKT_AREA_MORE_THAN_100_m2');
--
-- insert into sections (limit_value, calculation_id, name)
-- values (300.0, 2, 'Секция 2');
-- insert into consumers (number, section_id, name, type)
-- values (400, 2, 'Квартиры', 'FLATS_WITH_ELECTRIC_STOVE_10_kW');
-- insert into consumers (number, section_id, name, type)
-- values (100, 2, 'Коммерческие помещения', 'EMBEDDED_PREMISES_BKT_AREA_MORE_THAN_100_m2');
--
-- insert into sections (limit_value, calculation_id, name)
-- values (350.0, 2, 'Секция 2');
-- insert into consumers (number, section_id, name, type)
-- values (600, 3, 'Квартиры', 'FLATS_WITH_ELECTRIC_STOVE_10_kW');
-- insert into consumers (number, section_id, name, type)
-- values (100, 3, 'Коммерческие помещения', 'EMBEDDED_PREMISES_BKT_AREA_MORE_THAN_100_m2');
