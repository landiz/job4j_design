create table location(
    id serial primary key,
    shelf_number int unique,
    row_number int unique
);

create table group_item(
    id serial primary key,
    group_name text unique
);

create table item(
    id serial primary key,
    item_name text,
    serial_number int,
    location int references location(id) unique,
    group_item int references group_item(id)
);

create table items_groups(
    id serial primary key,
    item_id int references item(id),
    groupe_id int references group_item(id)

);