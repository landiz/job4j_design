insert into roles (name) values ('admin');
insert into users (name, role_id) values ('Andrey', 1);
insert into rules (name) values ('FullAccess');
insert into roles_rules (role_id, rule_id) values ((select id from roles where name = 'admin'), (select id from rules where name = 'FullAccess'));
insert into states (state) values ('Open');
insert into categories (category) values ('Files');
insert into items (name, user_id, category_id, state_id) values ('request', (select id from users where name = 'Andrey'), 1, 1);
insert into comments (comment, item_id) values ('comment', 1);
insert into attachments (link_to_file, item_id) values ('/Downloads/test.txt', 1);