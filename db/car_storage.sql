CREATE TABLE CAR_BODIES (ID serial PRIMARY KEY,
    NAME VARCHAR(255));

CREATE TABLE CAR_ENGINES (ID serial PRIMARY KEY,
    NAME VARCHAR(255));

CREATE TABLE CAR_TRANSMISSIONS (ID serial PRIMARY KEY,
    NAME VARCHAR(255));

CREATE TABLE CARS (ID serial PRIMARY KEY,
    NAME VARCHAR(255),
    BODY_ID int REFERENCES CAR_BODIES(ID),
    ENGINE_ID int REFERENCES CAR_ENGINES(ID),
    TRANSMISSION_ID int REFERENCES CAR_TRANSMISSIONS(ID));

INSERT INTO CAR_BODIES (NAME)
VALUES  ('Седан'),
        ('Купе'),
        ('Хэтчбек');

INSERT INTO CAR_ENGINES (NAME)
VALUES  ('Бензин'),
        ('Дизель'),
        ('Гибрид'),
        ('Электро');

INSERT INTO CAR_TRANSMISSIONS (NAME)
VALUES  ('Механическая'),
        ('Автоматическая'),
        ('Роботизированная'),
        ('Вариативная');

INSERT INTO CARS (NAME,
		BODY_ID,
		ENGINE_ID,
		TRANSMISSION_ID)
VALUES  ('ВАЗ 2101', 1, 1, 1),
		('Cayman', 3, 1, 2),
		('Panamera', 3, 2, 2),
		('Тесла', 1, 4, 4),
		('Prius', 1, 4, 4),
		('ВАЗ 2108', 3, 1, 1),
		('VW Polo', 3, 2, 4),
		('Эскаватор', NULL, 1, NULL),
		('Прицеп', null, null, null),
		('Автодом', null, 2, 1);

SELECT C.ID,
	C.NAME,
	B.ID AS Кузов,
	E.ID as Двигатель,
	T.ID as Трансмиссия
FROM CARS C
LEFT JOIN CAR_BODIES B ON C.BODY_ID = B.ID
LEFT JOIN CAR_ENGINES E ON C.ENGINE_ID = E.ID
LEFT JOIN CAR_TRANSMISSIONS T ON C.TRANSMISSION_ID = T.ID;

SELECT B.NAME
FROM CAR_BODIES B
LEFT  JOIN CARS C ON C.BODY_ID = B.ID
WHERE C.BODY_ID IS NULL;

SELECT E.NAME
FROM CAR_ENGINES E
LEFT  JOIN CARS C ON C.ENGINE_ID = E.ID
WHERE C.ENGINE_ID IS NULL;

SELECT T.NAME
FROM CAR_TRANSMISSIONS T
LEFT  JOIN CARS C ON C.TRANSMISSION_ID = T.ID
WHERE C.TRANSMISSION_ID IS NULL;
