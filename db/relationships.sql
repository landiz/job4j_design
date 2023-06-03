create table location(
    id serial primary key,
    shelf_number int unique,
    row_number int unique
);

create table group_item(
    id serial primary key,
    group_name text unique
);

create table type(
    id serial primary key,
    type_name text
);

create table item(
    id serial primary key,
    item_name text,
    serial_number int,
    location int references location(id) unique,
    type int references type(id)
);

create table items_groups(
    id serial primary key,
    item_id int references item(id),
    group_item_id int references group_item(id)

);