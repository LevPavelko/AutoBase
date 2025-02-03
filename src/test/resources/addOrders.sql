DELETE FROM Repair_Orders;
DELETE FROM Trips;
DELETE FROM Orders;
DELETE FROM Drivers;
DELETE FROM Cars;
DELETE FROM Dispatchers;
DELETE FROM Users;
DELETE FROM UserRole;

insert into UserRole(id, role)
values (1, 'ROLE_DISPATCHER');

insert into Users (id,first_name, last_name, age,  email, role_id, password)
values (1,'Alex', 'Popov', 26, 'popov.alex@gmail.com',1, 'password');

insert into Dispatchers (id, user_id)
values (1,1);

insert into orders (id,cargo_type,cargo_weight,request_data,Destination,dispatcher_id,has_trip)
values (1, 'DUSTED',6,'2025-01-31', 'Colorado',1, false);

insert into orders (id,cargo_type,cargo_weight,request_data,Destination,dispatcher_id,has_trip)
values (2, 'DUSTED',6,'2025-01-31', 'Colorado',1, false);