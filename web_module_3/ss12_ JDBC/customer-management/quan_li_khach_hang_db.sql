drop table if exists khach_hang;
create table khach_hang(
id int primary key  default 0,
ten varchar(45),
email varchar(45),
dia_chi varchar(45)
);

insert into khach_hang (id,ten, email, dia_chi) values
(1,"qdqwd","qdqwd","dqadsad");

