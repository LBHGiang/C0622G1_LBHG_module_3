drop database if exists quan_li_diem_thi;
create database quan_li_diem_thi;
use quan_li_diem_thi;

create table hoc_sinh (
ma_hoc_sinh int primary key,
ten_hoc_sinh varchar(45),
ngay_sinh date,
lop varchar(10),
gioi_tinh bit(1));

create table mon_hoc (
ma_mon_hoc int primary key,
ten_mon_hoc varchar(45)
);

create table giao_vien (
ma_giao_vien VARCHAR(20) PRIMARY KEY,
ten_giao_vien varchar(45),
so_dien_thoai varchar(10));

create table bang_diem (
ma_hoc_sinh int,
ma_mon_hoc int,
diem_thi int,
ngay_kiem_tra date,
primary key (ma_hoc_sinh,ma_mon_hoc),
foreign key (ma_hoc_sinh) REFERENCES hoc_sinh(ma_hoc_sinh),
foreign key (ma_mon_hoc) REFERENCES mon_hoc(ma_mon_hoc));

ALTER TABLE mon_hoc ADD ma_giao_vien VARCHAR(20);
ALTER TABLE mon_hoc add constraint FK_ma_giao_vien foreign key (ma_giao_vien) references giao_vien (ma_giao_vien);









