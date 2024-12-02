ALTER TABLE Drivers ALTER COLUMN password TYPE varchar(150);
insert into Drivers (first_name, last_name, age, email, password, experience_In_Years, is_busy)
values ('Michael', 'Sidovor', 39, 'michael.sidorov@gmail.com', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 5, false);