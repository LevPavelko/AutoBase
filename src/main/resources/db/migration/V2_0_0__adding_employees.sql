

insert into Users (first_name, last_name, age,  email, role_id, password)
values ('Michael', 'Sidovor', 39, 'michael.sidorov@gmail.com',1, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');
insert into Users (first_name, last_name, age,  email, role_id, password)
values ('Alex', 'Popov', 26, 'popov.alex@gmail.com',2, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');


UPDATE UserRole
SET role = 'ROLE_DRIVER'
WHERE id = 1;


UPDATE UserRole
SET role = 'ROLE_DISPATCHER'
WHERE id = 2;
