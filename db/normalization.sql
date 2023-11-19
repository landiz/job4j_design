create table gender
(
    id     serial primary key,
    gender text
);

create table person
(
    id        serial primary key,
    name      varchar(50),
    adress    text,
    gender_id int references gender (id)
);

create table movies
(
    id   serial primary key,
    name text
);

create table orders
(
    id        serial primary key,
    person_id int references person (id),
    movies_id int references movies (id)
);

insert into gender(gender)
values ('male'),
       ('female');
insert into person(name, adress, gender_id)
values ('Stas Petrov', 'Siviakov per. 27', 2),
       ('Sergey Ivanov', 'Zemlianoi val,67,35', 1),
       ('Oleg Vasin', 'pr. Lenina, 115, 23', 1);

insert into movies(name)
values ('1+1'),
       ('Старикам тут не место'),
       ('Нефть'),
       ('3 билборда');
insert into orders(person_id, movies_id)
values (1, 1),
       (1, 3),
       (2, 4),
       (2, 2),
       (3, 3);
select *
from orders;