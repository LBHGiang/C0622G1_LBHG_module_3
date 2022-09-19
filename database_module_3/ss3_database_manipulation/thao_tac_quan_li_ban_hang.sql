use quan_li_ban_hang;

-- Thêm dữ liệu vào trong 4 bảng --
insert into khach_hang values
(1,"Minh Quan",10),
(2,"Ngoc Oanh",20),
(3,"Hong Ha",50);

insert into dat_hang values
(1,1,"2006-3-21",Null),
(2,2,"2006-3-23",Null),
(3,1,"2006-3-16",Null);

insert into san_pham values
(1,"May Giat",3),
(2,"Tu Lanh",5),
(3,"Dieu Hoa",7),
(4,"Quat",1),
(5,"Bep Dien",2);

-- Thêm trường số lượng sản phẩm 
-- alter table chi_tiet_dat_hang
-- add column so_luong int;

insert into chi_tiet_dat_hang values
(1,1,3),
(1,3,7),
(1,4,2),
(2,1,1),
(3,1,8),
(2,5,4),
(2,3,3);

-- Hiển thị các thông tin  gồm oID, oDate, oPrice
-- của tất cả các hóa đơn trong bảng Order
select ma_dat_hang,ngay_dat_hang,tong_tien
from dat_hang;

-- Hiển thị danh sách các khách hàng đã mua hàng
-- và danh sách sản phẩm được mua bởi các khách
select kh.ten_khach_hang, sp.ten_san_pham
from dat_hang dh 
join khach_hang kh
on dh.ma_khach_hang = kh.ma_khach_hang
join chi_tiet_dat_hang ctdh
on dh.ma_dat_hang = ctdh.ma_dat_hang
join san_pham sp
on ctdh.ma_san_pham = sp.ma_san_pham;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select ten_khach_hang as ten_khach_hang_chua_mua_san_pham_nao
from khach_hang
left join dat_hang 
on khach_hang.ma_khach_hang = dat_hang.ma_khach_hang
where dat_hang.ma_khach_hang is null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn

select dh.ma_dat_hang, dh.ngay_dat_hang, sum(ctdh.so_luong * sp.gia) as tong_tien
from dat_hang dh
join chi_tiet_dat_hang ctdh 
on dh.ma_dat_hang = ctdh.ma_dat_hang
join san_pham sp
on ctdh.ma_san_pham = sp.ma_san_pham
group by ctdh.ma_dat_hang;


desc chi_tiet_dat_hang;
desc dat_hang;
select * from dat_hang;
