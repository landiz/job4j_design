create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id) not null,
	expired_date date,
	price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('ОВОЩИ'), ('БАКАЛЕЯ'), ('ХЛЕБ');
insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2023-05-29', 100), ('Сыр моцарелла', 1, '2023-06-29', 200), ('Сыр эдам', 1, '2023-07-19', 300), ('Сыр гауда', 1, '2023-07-20', 250), ('Сыр российский', 1, '2023-07-09', 220);
insert into product (name, type_id, expired_date, price) values ('Молоко 3,5', 2, '2023-05-29', 80), ('Молоко 4,5', 2, '2023-06-30', 90), ('Кефир 3,5', 2, '2023-07-03', 100), ('Мороженое экскимо', 2, '2023-07-20', 150), ('Простокваша', 2, '2023-07-09', 120);
insert into product (name, type_id, expired_date, price) values ('Макароны', 3, '2023-09-29', 80), ('Гречка', 3, '2023-09-30', 90), ('Сахар', 3, '2024-07-03', 100), ('Спички', 3, '2025-07-20', 50), ('Мука', 3, '2023-09-09', 120);
insert into product (name, type_id, expired_date, price) values ('Хлеб белый', 3, '2023-07-04', 75), ('Хлеб черный', 4, '2023-07-13', 50), ('Хлеб дарницкий', 4, '2024-07-03', 40), ('Булка московская', 4, '2025-07-12', 50), ('Баранки', 4, '2022-09-09', 120);

SELECT P.NAME AS ПРОДУКТ,
	T.NAME AS ТИП
FROM PRODUCT P
JOIN TYPE T ON P.TYPE_ID = T.ID
WHERE T.NAME = 'СЫР';

SELECT *
FROM PRODUCT
WHERE NAME ilike '%мороженое%';

SELECT *
FROM PRODUCT
WHERE EXPIRED_DATE < CURRENT_DATE;

SELECT P.NAME,
	P.PRICE
FROM PRODUCT P
JOIN
	(SELECT TYPE_ID,
			MAX(PRICE) PRICE
		FROM PRODUCT
		GROUP BY TYPE_ID) P2 ON P2.PRICE = P.PRICE
AND P2.TYPE_ID = P.TYPE_ID ;

SELECT T.NAME AS ТИП,
	COUNT(*) AS КОЛИЧЕСТВО
FROM PRODUCT P
JOIN TYPE T ON T.ID = P.TYPE_ID
GROUP BY T.NAME;

SELECT P.NAME AS ПРОДУКТ,
	T.NAME AS ТИП
FROM PRODUCT P
JOIN TYPE T ON P.TYPE_ID = T.ID
WHERE T.NAME IN ('СЫР','МОЛОКО');

SELECT T.*
FROM TYPE T
JOIN
	(SELECT TYPE_ID
		FROM PRODUCT
		GROUP BY TYPE_ID
		HAVING COUNT(TYPE_ID) < 10) P ON P.TYPE_ID = T.ID;

SELECT P.NAME ПРОДУКТ,
	T.NAME ТИП
FROM PRODUCT P
JOIN TYPE T ON T.ID = P.TYPE_ID;
