CREATE TABLE IF NOT EXISTS  Dispatchers (
    id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    email varchar(50) not null,
    password varchar(50) not null
);

CREATE TABLE IF NOT EXISTS Cars(
    id serial primary key,
    is_free BOOLEAN not null,
    capacity BIGINT  not null,
    is_broken BOOLEAN not null
);
CREATE TABLE IF NOT EXISTS Drivers (
    id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    age integer not null,
    email varchar(30) not null,
    password varchar(50) not null,
    experience_In_Years int not null,
    is_busy BOOLEAN not null
);

CREATE TABLE IF NOT EXISTS Orders(
    id serial primary key,
    cargo_type varchar(50) not null,
    cargo_weight integer not null,
    request_data date not null,
    destination varchar(50) not null,
    dispatcher_id integer,
    FOREIGN KEY(dispatcher_id) REFERENCES Dispatchers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Repair_Orders(
    id serial primary key,
    driver_id integer not null,
    car_id integer not null,
    description varchar(50) not null,
    order_id integer,
    request_date date not null,
    is_repaired BOOLEAN not null,
    FOREIGN KEY(driver_id) REFERENCES Drivers(id) ON DELETE CASCADE,
    FOREIGN KEY(car_id) REFERENCES Cars(id) ON DELETE CASCADE,
    FOREIGN KEY(order_id) REFERENCES Orders(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Trips(
    id serial primary key,
    driver_id integer,
    car_id integer,
    start_date date not null,
    end_date date not null,
    price integer not null,
    order_id integer,
    FOREIGN KEY(driver_id) REFERENCES Drivers(id) ON DELETE CASCADE,
    FOREIGN KEY(car_id) REFERENCES Cars(id) ON DELETE CASCADE,
    FOREIGN KEY(order_id) REFERENCES Orders(id) ON DELETE CASCADE
)
