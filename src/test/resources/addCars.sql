DELETE FROM Repair_Orders;
DELETE FROM Trips;
DELETE FROM Orders;
DELETE FROM Drivers;
DELETE FROM Cars;
DELETE FROM Dispatchers;
DELETE FROM Users;
DELETE FROM UserRole;

insert into Cars (id, type, is_free, capacity, is_broken)
values (1,'REFRIGERATED_VAN', true,2, false );

insert into Cars (id, type, is_free, capacity, is_broken)
values (2,'REFRIGERATED_VAN', true,2, false );