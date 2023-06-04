xcreate table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age)
values ('BC', 100000);
insert into fauna(name, avg_age, discovery_date)
values ('fish', 20,  '2019-09-02');
insert into fauna(name, avg_age, discovery_date)
values ('BE', 3000, '1930-09-03');
insert into fauna(name, avg_age, discovery_date)
values ('BF', 15000, '1950-0-00');
insert into fauna(name, avg_age, discovery_date)
values ('BG', 20000, '2016-09-05');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select name from fauna where discovery_date < '1950-01-01';