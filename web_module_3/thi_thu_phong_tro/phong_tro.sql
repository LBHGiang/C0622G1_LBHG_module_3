drop database if exists quan_li_phong_tro;
create database quan_li_phong_tro;
use quan_li_phong_tro;

drop table if exists thanh_toan;
create table thanh_toan (
ma_thanh_toan int primary key auto_increment,
hinh_thuc varchar(45),
ton_tai bit(1) default 0);

drop table if exists phong_tro;
create table phong_tro (
ma_phong_tro int primary key auto_increment,
ho_ten varchar(45) not null,
so_dien_thoai varchar(45) not null,
ngay_thue Date not null,
ma_thanh_toan int not null,
ghi_chu varchar(45),
ton_tai bit(1) default 0,
foreign key (ma_thanh_toan) references thanh_toan(ma_thanh_toan)
);

INSERT INTO `quan_li_phong_tro`.`thanh_toan` 
(`ma_thanh_toan`, `hinh_thuc`, `ton_tai`) VALUES 
(1, 'Tháng', 0),
(2, 'Quý', 0),
(3, 'Năm', 0);

INSERT INTO `quan_li_phong_tro`.`phong_tro` (`ma_phong_tro`, `ho_ten`, `so_dien_thoai`, `ngay_thue`, `ma_thanh_toan`, `ghi_chu`, `ton_tai`) 
VALUES 
(1, 'Lê Hoàng Giang', '0123453454', '2022-11-21', 2, 'Cần điều hòa', 0),
(2, 'Thanh Hải', '1346323423', '2022-11-30', 1, 'Sơn mới', 0),
(3, 'Quang Vinh', '1052342331', '2023-01-23', 2, 'Cửa thủng', 0),
(4, 'Cát Uyên', '14142534545', '2022-11-22', 3,null, 0),
(5, 'Hữu Trường', '2135445243', '2023-11-22', 2, 'Nhà dột', 0),
(6, 'Thế Sơn', '34324234234', '2024-03-21', 3,null, 0);




INSERT INTO `data_furama`.`position` (`id`, `name`) 
VALUES ('1', 'Lễ tân'),
('2', 'Phục vụ'),
('3', 'Chuyên viên'),
('4', 'Giám sát'),
('5', 'Quản lý'),
('6', 'Giám đốc');


