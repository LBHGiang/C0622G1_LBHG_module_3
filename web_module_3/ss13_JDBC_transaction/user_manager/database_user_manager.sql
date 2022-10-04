DROP DATABASE IF EXISTS login_data;
CREATE DATABASE login_data;
USE login_data;

drop table if exists users;
create table users (
 id  int(3) NOT NULL AUTO_INCREMENT,
 name varchar(120) NOT NULL,
 email varchar(220) NOT NULL,
 country varchar(120),
 PRIMARY KEY (id)
);

insert into users(name, email, country) values('Trường','minh@codegym.vn','Viet Nam');
insert into users(name, email, country) values('Vinh','kante@che.uk','Kenia');
insert into users(name, email, country) values('Giang','gianghoang@che.uk','Lao');
insert into users(name, email, country) values('Hải','thanhhai@che.uk','Thai');

select * from users
where country like '%%'; 

select * from users
order by 'name';