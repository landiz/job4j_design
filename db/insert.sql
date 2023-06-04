insert into users (name) values ('Andrey');
insert into roles (name, user_id) values ('admin', (select id from users where name = 'Andrey'));
insert into rules (name) values ('FullAccess');
insert into roles_rules (role_id, rule_id) values ((select id from roles where name = 'admin'), (select id from rules where name = 'FullAccess'));
insert into items (name, user_id) values ('request', (select id from users where name = 'Andrey'));
insert into comments (comment, item_id) values ('comment', 1);
insert into attachments (link_to_file, item_id) values ('/Downloads/test.txt', 1);
insert into categories (category, item_id) values ('Files', 1);
insert into states (state, item_id) values ('Open', 1);

