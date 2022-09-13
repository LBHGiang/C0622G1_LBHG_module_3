drop database if exists quan_ly_sinh_vien;
create database quan_ly_sinh_vien;
use quan_ly_sinh_vien;

create table lop(
ma_lop Int Not Null  Primary key  auto_increment ,
ten_lop varchar (60) Not Null ,
ngay_nhap_hoc Datetime Not null ,
trang_thai Bit );

create table hoc_sinh(
ma_hoc_sinh Int Not null  Primary key  auto_increment ,
ten_hoc_sinh varchar (30) Not Null ,
dia_chi varchar (50) ,
so_dien_thoai varchar (20) ,
trang_thai Bit ,
ma_lop Int Not null,
foreign key (ma_lop) references lop (ma_lop)
);

create table mon_hoc(
ma_mon_hoc Int Not null  Primary key  auto_increment ,
ten_mon_hoc varchar (30) Not Null ,
credit Tinyint Not null default 1 check (credit >= 1),
trang_thai Bit Default 1 );

create table diem(
ma_diem Int Not null  Primary key  auto_increment ,
ma_mon_hoc Int Not Null Unique key ,
ma_hoc_sinh Int Not null Unique key ,
diem Float Default 0 check (diem between 0 and 100) ,
thoi_gian Tinyint Default 1,
foreign key (ma_mon_hoc) references mon_hoc (ma_mon_hoc),
foreign key (ma_hoc_sinh) references hoc_sinh (ma_hoc_sinh)
);

select * from lop;
select * from hoc_sinh;
select * from mon_hoc;
select * from diem;




	