create table courses(
    id serial primary key,
    course_name text,
	course_duration int
);

create table students(
    id serial primary key,
    student_name text,
    student_last_name text,
    course_id int references courses(id)
);

insert into courses(course_name, course_duration) values ('programmer', 5);
insert into courses(course_name, course_duration) values ('project manager', 3);
insert into courses(course_name, course_duration) values ('director', 7);
insert into students(student_name, student_last_name, course_id) values('Alex', 'Pushkin', 1);
insert into students(student_name, student_last_name, course_id) values('Polina', 'Kozlova', 1);
insert into students(student_name, student_last_name, course_id) values('Serg', 'Ivanov', 1);
insert into students(student_name, student_last_name ,course_id) values ('Vasiliy', 'Petrom', 2);
insert into students(student_name, student_last_name, course_id) values ('Polina', 'Sidorova', 3);

select pp.student_name, pp.student_last_name, p.course_name
from students as pp join courses as p on pp.course_id = p.id;

select pp.student_name as Имя, p.course_duration as "Срок обучения"
from students as pp join courses as p on pp.course_id = p.id;

select p.course_name as Курс, p.course_duration as "Срок обучения", pp.student_last_name as Фамилия
from students as pp join courses as p on pp.course_id = p.id where pp.student_name = 'Polina';
