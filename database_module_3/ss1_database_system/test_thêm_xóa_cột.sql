ALTER TABLE class
add column last_name varchar(40) not null first;

ALTER TABLE class
drop column last_name;


select * from class;