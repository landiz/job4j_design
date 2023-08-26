CREATE TABLE IF NOT EXISTS products
(
    id       serial PRIMARY KEY,
    name     varchar(50),
    producer varchar(50),
    count    integer DEFAULT 0,
    price    integer
);

INSERT INTO products(name, producer, count, price)
VALUES ('product_1', 'producer_1', 5, 10),
       ('product_2', 'producer_2', 15, 230),
       ('product_3', 'producer_3', 0, 100);

CREATE OR REPLACE PROCEDURE delete_products_zero_count()
    LANGUAGE 'plpgsql' AS
$$
BEGIN
    DELETE FROM products WHERE id IN (SELECT id FROM products WHERE count = 0);
END;
$$;

CREATE OR REPLACE FUNCTION f_delete_by_id(id_delete integer)
    RETURNS table
            (
                name  varchar,
				count integer,
                price integer
            )
    LANGUAGE sql
AS
$$
DELETE
FROM products
WHERE id <= id_delete
RETURNING NAME, count, price;
$$;

CALL delete_products_zero_count();
select f_delete_by_id(4);
