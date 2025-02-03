DELETE FROM Repair_Orders;
DELETE FROM Trips;
DELETE FROM Orders;
DELETE FROM Drivers;
DELETE FROM Cars;
DELETE FROM Dispatchers;
DELETE FROM Users;
DELETE FROM UserRole;

insert into UserRole(id, role)
values (1, 'ROLE_DRIVER');

insert into Users (id, first_name,last_name,age,email,role_id,password)
values  (1,'Michael', 'Sidovor', 39, 'michael.sidorov@gmail.com',1, 'password');

insert into Users (id, first_name,last_name,age,email,role_id,password)
values  (2,'Michael', 'Sidovor', 39, 'michael.sidorov@gmail.com',1, 'password');


insert into Drivers (id, user_id,experience_In_Years,is_busy)
values (1, 1,7,false);

insert into Drivers (id, user_id,experience_In_Years,is_busy)
values (2, 2,7,false);
