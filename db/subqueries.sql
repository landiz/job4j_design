CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers
VALUES (1, 'Иван', 'Козлов', 50, 'Россия'),
       (2, 'Женя', 'Ленин', 23, 'Россия'),
       (3, 'Андрей', 'Физикофф', 23, 'Россия'),
       (4, 'Алмаз', 'Алмазов', 49, 'Россия'),
       (5, 'Алексей', 'Никитин', 85, 'Россия'),
       (6, 'Петр', 'Васин', 85, 'Россия');

INSERT INTO orders
VALUES (1, 5, 2),
       (2, 8, 4),
       (3, 7, 4),
       (4, 7, 3),
       (5, 7, 5);

SELECT *
FROM CUSTOMERS
WHERE AGE =
      (SELECT MIN(AGE)
       FROM CUSTOMERS);


SELECT FIRST_NAME,
       LAST_NAME
FROM CUSTOMERS
WHERE CUSTOMERS.ID NOT IN
      (SELECT ORDERS.CUSTOMER_ID
       FROM ORDERS);