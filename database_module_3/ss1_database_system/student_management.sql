drop database if exists teacherstudent_management;
create database student_management;
use student_management;
create table teacher (
`ma_giao_vien` int primary key not null,
`ten_giao_vien` varchar(45),
`tuoi` int,
`ten_nuoc` varchar(45)
);
create table student (
`ma_hoc_sinh` int primary key not null,
`ten_hoc_sinh` varchar(45),
`tuoi` int,
`ten_nuoc` varchar(45)
);
create table class (
`ma_lop` int primary key not null,
`ten_lop` varchar(45)
);
