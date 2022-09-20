use co_so_du_lieu_furama;

-- Task 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.

delete from nhan_vien
where nhan_vien.ma_nhan_vien not in
(select ma_nhan_vien 
from hop_dong
where year(hop_dong.ngay_lam_hop_dong) in (2019,2020,2021));

-- Task 17.	Cập nhật thông tin những khách hàng có ten_loai_khach
-- từ Platinum lên Diamond đã từng đặt phòng với 
-- Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.
-- dich_vu.chi_phi_thue + ifnull(hop_dong_chi_tiet.so_luong * dich_vu_di_kem.gia,0) as tong_tien

create or replace view don_gia_hoa_don as
select  khach_hang.ma_khach_hang, khach_hang.ho_ten, loai_khach.ten_loai_khach,
hop_dong.ma_hop_dong, dich_vu.ten_dich_vu, hop_dong.ngay_lam_hop_dong, hop_dong.ngay_ket_thuc,
dich_vu.chi_phi_thue + ifnull(hop_dong_chi_tiet.so_luong * dich_vu_di_kem.gia,0) as tong_tien
from khach_hang 
left join loai_khach on khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
left join hop_dong on khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
left join dich_vu on hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
left join hop_dong_chi_tiet on hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
left join dich_vu_di_kem on hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
group by  ma_hop_dong, ma_khach_hang
order by ma_khach_hang
;

update khach_hang
set ma_loai_khach = 1 
where ma_khach_hang in(
select ma_khach_hang
from don_gia_hoa_don
where ten_loai_khach = "Platinium" and year (ngay_lam_hop_dong) = 2021
group by ma_khach_hang
having sum(tong_tien) >= 100000);

-- Task 18.	Xóa những khách hàng có hợp đồng trước năm 2021 
-- (chú ý ràng buộc giữa các bảng).
-- Cách 1: Xóa cứng
set FOREIGN_KEY_CHECKS=0;
delete from khach_hang
where ma_khach_hang in
(select ma_khach_hang
from hop_dong
where year(ngay_lam_hop_dong) < 2021);
select * from khach_hang;

-- Cách 2: Xóa mềm
alter table khach_hang
add trang_thai bit(1) default 1 after dia_chi;

-- Xóa
update khach_hang
set trang_thai = 0 
where ma_khach_hang in
(select ma_khach_hang
from hop_dong
where year(ngay_lam_hop_dong) < 2021);

-- Task 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần
-- trong năm 2020 lên gấp đôi.

update dich_vu_di_kem
set gia = gia * 2
where ma_dich_vu_di_kem in
(select hdct.ma_dich_vu_di_kem
from hop_dong_chi_tiet hdct
join hop_dong hd on hdct.ma_hop_dong = hd.ma_hop_dong
where year (hd.ngay_lam_hop_dong) = 2020
group by hdct.ma_dich_vu_di_kem
having sum(hdct.so_luong) > 10);

select * from dich_vu_di_kem;

-- Task 20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng
-- có trong hệ thống bao gồm id (ma_nhan_vien, ma_khach_hang),
-- ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.
select "Nhân Viên" as doi_tuong, ma_nhan_vien as id, ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
from nhan_vien
union
select "Khách Hàng" as doi_tuong, ma_khach_hang, ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
from khach_hang
order by doi_tuong;

