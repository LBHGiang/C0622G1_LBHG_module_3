drop database if exists quan_li_ban_hang;
create database quan_li_ban_hang;
use quan_li_ban_hang;

create table khach_hang(
ma_khach_hang int primary key,
ten_khach_hang varchar(45),
tuoi int
);

create table dat_hang(
ma_dat_hang int primary key,
ma_khach_hang int,
ngay_dat_hang date,
tong_tien double,
foreign key (ma_khach_hang) references khach_hang(ma_khach_hang)
);

create table san_pham(
ma_san_pham int primary key,
ten_san_pham varchar(45),
gia double
);

create table chi_tiet_dat_hang(
ma_dat_hang int,
ma_san_pham int,
primary key (ma_dat_hang,ma_san_pham),
foreign key (ma_dat_hang) references dat_hang(ma_dat_hang),
foreign key (ma_san_pham) references san_pham(ma_san_pham)
);