DELETE FROM timetable;
DELETE FROM subjects;
DELETE FROM teachers;
DELETE FROM students;
DELETE FROM groups;

INSERT INTO teachers
(id, firstname, lastname, phone)
VALUES      (1,'Kiril','Sergeev', '0976962128'),
            (2,'Dmirtiy','Samoilov', '0958868911'),
            (3,'Broneslav','Podgorniy', '0952684442'),
            (4,'Vyacheslav','Samoilov', '0978874731');

INSERT INTO groups
(id, group_name, description)
VALUES      (1,'jm-23','This is jm-23 group'),
            (2,'jm-25','This is jm-25 group');

INSERT INTO subjects
(id, subject_name, teacher_id)
VALUES      (1,'mathematics',1),
            (2,'biology',4),
            (3,'physics',2);

INSERT INTO students
(id, firstname, lastname, phone, studticketnumber, group_id)
VALUES      (1,'Igor', 'Pakin', '0953913981', 51492526, 1),
            (2,'Svyatoslav', 'Zagudaev', '0505862844', 12735211, 1),
            (3,'Aleksandr', 'Panteleev', '0953117511', 25648911, 2),
            (4,'Egor', 'Kadancev', '0973644492', 86192525, 2);

INSERT INTO timetable
(id, dateofday, pairsnumber, subject_id, group_id, auditory, teacher_id)
VALUES      (1,'2022-12-24', 1, 1, 2, 'corpus5--155', 4),
            (2,'2022-12-24', 2, 2, 1, 'corpus5--205', 1);

