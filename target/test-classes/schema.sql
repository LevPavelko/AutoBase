DROP TABLE IF EXISTS timetable;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS groups;

CREATE TABLE IF NOT EXISTS groups (
    id SERIAL PRIMARY KEY,
    group_name character varying(30),
    description varchar (30)
    );

CREATE TABLE IF NOT EXISTS teachers (
    id SERIAL PRIMARY KEY,
    firstName  varchar (30),
    lastName varchar (30),
    phone varchar (30)
    );

CREATE TABLE IF NOT EXISTS subjects (
    id SERIAL PRIMARY KEY,
    subject_name  varchar (30),
    teacher_id integer,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    firstName  varchar (30),
    lastName varchar (30),
    phone varchar (30),
    studTicketNumber integer,
    group_id integer,
    FOREIGN KEY (group_id) REFERENCES groups (id)  ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS timetable (
    id SERIAL PRIMARY KEY,
    dateOfDay date,
    pairsNumber integer,
    subject_id integer,
    group_id integer,
    auditory  varchar (30),
    teacher_id integer,
    FOREIGN KEY (subject_id) REFERENCES subjects (id)  ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id)  ON DELETE CASCADE
    );

