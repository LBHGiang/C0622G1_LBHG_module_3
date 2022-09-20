use co_so_du_lieu_furama;

-- Task 2
-- Hiển thị thông tin của tất cả nhân viên
-- có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
select *
from nhan_vien
where ho_ten regexp "^\\p{L}+(\\s\\p{L}+)*(\\s[HKT]\\p{L}*)$"
and length(ho_ten) <=15;

-- Task 3
-- Hiển thị khách hàng có độ tuổi từ 18 đến 50
-- và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
select *
from khach_hang
where 
(timestampdiff(year,ngay_sinh, CURDATE()) between 18 and 50)
and (dia_chi like "%Đà Nẵng" or dia_chi like "%Quảng Trị");

-- Task 4
-- Đếm xem mỗi khách hàng đã từng đặt phòng bao nhiêu lần.
-- Kết quả sắp xếp tăng dần
-- Chỉ đếm những khách hàng có loại khách hàng là “Diamond”.
select  khach_hang.ma_khach_hang, khach_hang.ho_ten, count(hop_dong.ma_khach_hang) as so_lan
from khach_hang
join loai_khach
on khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
join hop_dong
on khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
where loai_khach.ten_loai_khach = 'Diamond'
group by hop_dong.ma_khach_hang
order by so_lan asc;

-- Task 5
-- Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, 
-- ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien 
-- tổng tiền = Chi Phí Thuê + Số Lượng * Giá
-- Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet
-- cho tất cả các khách hàng đã từng đặt phòng 
-- (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).

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