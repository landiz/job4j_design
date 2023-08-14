create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

INSERT INTO DEPARTMENTS (NAME)
VALUES ('Департамент развития цифровых компетенций и образования'),
							('Департамент развития отрасли информационных технологий'),
							('Департамент развития отрасли информационных технологий '),
							('Правовой департамент '),
							('Административный департамент '),
							('Департамент развития массовых коммуникаций и международного сотрудничества '),
							('Департамент обеспечения кибербезопасности '),
							('Департамент экономики и финансов ');

INSERT INTO EMPLOYEES (NAME, DEPARTMENT_ID)
VALUES  ('Сергей', 1),
		('Андрей', 2),
		('Василий', 3),
		('Екатерина', 4),
		('Александра', 5),
		('Полина', 6),
		('Аркадий', 5),
		('Зоя', 5),
		('Павел', 3),
		('Ким', 1),
		('Ира', 5);

SELECT *
FROM EMPLOYEES E
FULL JOIN DEPARTMENTS D ON D.ID = E.DEPARTMENT_ID;

SELECT *
FROM EMPLOYEES E
LEFT JOIN DEPARTMENTS D ON D.ID = E.DEPARTMENT_ID;

SELECT *
FROM EMPLOYEES E
RIGHT JOIN DEPARTMENTS D ON D.ID = E.DEPARTMENT_ID;

SELECT *
FROM EMPLOYEES
CROSS JOIN DEPARTMENTS;

SELECT *
FROM DEPARTMENTS D
LEFT JOIN EMPLOYEES E ON E.DEPARTMENT_ID = D.ID
WHERE E.DEPARTMENT_ID ISNULL;

SELECT *
FROM EMPLOYEES E
LEFT JOIN DEPARTMENTS D ON D.ID = E.DEPARTMENT_ID;

SELECT *
FROM EMPLOYEES E
RIGHT JOIN DEPARTMENTS D ON D.ID = E.DEPARTMENT_ID
WHERE E.DEPARTMENT_ID NOTNULL;

INSERT INTO TEENS (NAME, GENDER)
VALUES ('Михаил', 'М'),
	    ('Василий', 'М'),
		('Петр', 'М'),
		('Андрей', 'М'),
		('Ольга', 'Ж'),
		('Ирина', 'Ж'),
		('Оксана', 'Ж'),
		('Катя', 'Ж'),
		('Света', 'Ж'),
		('Илья', 'М');

SELECT *
FROM TEENS T
CROSS JOIN TEENS T1
WHERE T1.GENDER != T.GENDER;
