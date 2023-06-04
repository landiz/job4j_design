create table roles(
    id serial  primary key,
    name text
);

create table users(
    id serial primary key,
    name text,
    role_id int references roles(id) not null

);

create table rules(
    id serial primary key,
    name text
);

create table roles_rules(
    id serial primary key,
    role_id int references roles(id) not null,
    rule_id int references rules(id) not null
);

create table categories(
    id serial primary key,
    category text
);

create table states(
    id serial primary key,
    state text
);

create table items(
    id serial primary key,
    name text,
    user_id int references users(id) not null,
    category_id int references categories(id) not null,
    state_id int references states(id) not null
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references items(id) not null
);

create table attachments(
    id serial primary key,
    link_to_file text,
    item_id int references items(id) not null
);