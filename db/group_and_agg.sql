create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices(name, price) values ('iphone6',1000), ('iphone8',2000), ('iphoneX',5500), ('ipad',5200), ('makbook',6500), ('macstudio',7400), ('vision pro',8500);
insert into devices_people(device_id, people_id) values (7, 1), (1, 1), (2, 2), (6, 2), (3, 3), (5, 3), (4, 2);

select avg(price) from devices;

select
	p.name, avg(d.price)
from people p,
	 devices d, devices_people dp
where dp.people_id=p.id
and dp.device_id = d.id
group by p.name;

select
	p.name, avg(d.price)
from people p,
	 devices d, devices_people dp
where dp.people_id=p.id
and dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;