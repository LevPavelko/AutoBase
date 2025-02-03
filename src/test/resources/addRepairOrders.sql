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

insert into UserRole(id, role)
values (2, 'ROLE_DISPATCHER');

insert into Users (id, first_name,last_name,age,email,role_id,password)
values  (1,'Michael', 'Sidovor', 39, 'michael.sidorov@gmail.com',1, 'password');

insert into Users (id,first_name, last_name, age,  email, role_id, password)
values (2,'Alex', 'Popov', 26, 'popov.alex@gmail.com',2, 'password');

insert into Drivers (id, user_id,experience_In_Years,is_busy)
values (1, 1,7,false);

insert into Cars (id, type, is_free, capacity, is_broken)
values (1,'REFRIGERATED_VAN', true,2, false );

insert into Dispatchers (id, user_id)
values (1,2);

insert into Orders (id,cargo_type,cargo_weight, request_data , Destination, dispatcher_id, has_trip)
values(1,'CONTAINER', '20', DATE'2024.12.19', 'Aspen,Colorado', 1, false);

insert into Trips (id, driver_id, car_id,start_date,end_date,price,order_id)
values (1, 1, 1,'2025-01-31', null, 1500, 1);

insert into Repair_Orders (id, driver_id, car_id, description, trip_id, request_date,is_Repaired)
values (1,1,1,'lalala', 1, '2025-01-31', false);

insert into Repair_Orders (id, driver_id, car_id, description, trip_id, request_date,is_Repaired)
values (2,1,1,'lalala', 1, '2025-01-31', false);