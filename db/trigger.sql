create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
	referencing new table as inserted
    for each statement
    execute procedure tax();

create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
    before insert on products
    for each row
    execute procedure tax_row();

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history_of_price()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date)
        values(new.name, new.price, NOW());
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_of_price
    after insert on products
    for each row
    execute procedure history_of_price();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 30, 100);
select * from products;
select * from history_of_price;

