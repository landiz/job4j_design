CREATE TABLE IF NOT EXISTS lamps
(
    id    serial PRIMARY KEY,
    color text,
    state text
);

INSERT INTO lamps(color, state)
VALUES ('red', 'on'),
       ('blue', 'off');