drop database if exists quan_li_vat_tu;
create database quan_li_vat_tu;
use quan_li_vat_tu;

create table nha_cung_cap(
ma_nha_cung_cap int primary key,
ten_nha_cung_cap varchar(45),
dia_chi varchar(45)
);

create table so_dien_thoai( 
id int  primary key auto_increment,
ma_nha_cung_cap int,
so_dien_thoai varchar(10) unique,
foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);

create table don_dat_hang(
ma_don_dat_hang int primary key,
ngay_dat_hang date,
ma_nha_cung_cap int,
foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);

create table phieu_nhap(
so_phieu_nhap int primary key,
ngay_nhap date
);

create table phieu_xuat(
so_phieu_xuat int primary key,
ngay_xuat date
);

create table vat_tu(
ma_vat_tu int primary key,
ten_vat_tu varchar(45)
);

create table chi_tiet_dat_hang(
ma_don_dat_hang int,
ma_vat_tu int,
primary key (ma_don_dat_hang,ma_vat_tu),
foreign key (ma_don_dat_hang) references don_dat_hang(ma_don_dat_hang),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_phieu_nhap(
so_phieu_nhap int,
ma_vat_tu int,
gia_nhap double,
so_luong int,
primary key (so_phieu_nhap,ma_vat_tu),
foreign key (so_phieu_nhap) references phieu_nhap(so_phieu_nhap),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_phieu_xuat(
so_phieu_xuat int,
ma_vat_tu int,
gia_xuat double,
so_luong int,
primary key (so_phieu_xuat,ma_vat_tu),
foreign key (so_phieu_xuat) references phieu_xuat(so_phieu_xuat),
foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

