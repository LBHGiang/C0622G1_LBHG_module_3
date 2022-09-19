drop database if exists demo;
create database demo;
use demo;
create table san_pham(
id int primary key auto_increment,
ma_san_pham int,
ten_san_pham varchar(50),
gia_san_pham double,
so_luong int,
mo_ta varchar(50),
trang_thai bit(1));

insert into san_pham values 
(1,10,"Điện thoại",25,10,"Mới",0),
(2,11,"Ô tô",55,2,"Cũ",1),
(3,12,"Máy giặt",3,15,"Cũ",1),
(4,13,"Máy hút bụi",2,4,"Mới",1);


-- Sử dụng index
create unique index i_san_pham_1
on san_pham(ma_san_pham);

create unique index i_san_pham_2
on san_pham(ten_san_pham,gia_san_pham);

EXPLAIN select *
from san_pham
where ma_san_pham = 12;

EXPLAIN select *
from san_pham
where gia_san_pham = 25;

EXPLAIN select *
from san_pham
where gia_san_pham = 25 and ten_san_pham = "Điện thoại";

-- Sử dụng view
create view thong_tin as
select ma_san_pham, ten_san_pham, gia_san_pham, trang_thai
from san_pham;

select * from thong_tin;

create or replace view thong_tin as
select ma_san_pham, ten_san_pham, gia_san_pham
from san_pham;

drop view thong_tin;

-- Tạo store procedure
-- Lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
CREATE PROCEDURE lay_thong_tin_san_pham()
BEGIN
  SELECT * FROM san_pham;
END //
DELIMITER ;

call lay_thong_tin_san_pham();

-- Tạo store procedure thêm một sản phẩm mới

DELIMITER //
CREATE PROCEDURE them_san_pham_moi(
in ma_san_pham int,
in ten_san_pham varchar(50),
in gia_san_pham double,
in so_luong int,
in mo_ta varchar(50),
in trang_thai bit(1)
)
BEGIN
  insert into san_pham (ma_san_pham,ten_san_pham,gia_san_pham,so_luong,mo_ta,trang_thai)
  values (ma_san_pham,ten_san_pham,gia_san_pham,so_luong,mo_ta,trang_thai );
END //
DELIMITER ;

call them_san_pham_moi(14,"Bàn",1,4,"mới",1);
call lay_thong_tin_san_pham();

-- Sửa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE sua_thong_tin_san_pham(
in id int,
in ma_san_pham_moi int,
in ten_san_pham_moi varchar(50),
in gia_san_pham_moi double,
in so_luong_moi int,
in mo_ta_moi varchar(50),
in trang_thai_moi bit(1)
)
BEGIN
	update san_pham
    set 
    ma_san_pham = ma_san_pham_moi,
	ten_san_pham = ten_san_pham_moi,
	gia_san_pham = gia_san_pham_moi,
    so_luong = so_luong_moi,
    mo_ta = mo_ta_moi,
    trang_thai = trang_thai_moi
    where san_pham.id = id;
END //
DELIMITER ;

call sua_thong_tin_san_pham(3,15,"Máy giặt",4,15,"cũ",0);
call lay_thong_tin_san_pham();

-- Xoá sản phẩm theo id
DELIMITER //
CREATE PROCEDURE xoa_san_pham (in id int)
BEGIN
	delete from san_pham
    where san_pham.id = id;
END //
DELIMITER ;

call xoa_san_pham(2);
call lay_thong_tin_san_pham();
