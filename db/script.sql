create table students
(
    student_id   serial primary key,
        student_name text,
    student_last_name text,
    group_id int
);
x
insert into students (student_name, student_last_name, group_id) values ('Petr', 'Petrov', 77)

update students
	set student_name='Alex', group_id= 7
	WHERE student_id=1;

delete from students
	WHERE student_id=1;