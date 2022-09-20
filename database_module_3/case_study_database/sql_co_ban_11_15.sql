use co_so_du_lieu_furama;

-- Task 11.	
-- Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng 
-- bởi những khách hàng có ten_loai_khach là “Diamond” \
-- và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.
select ten_dich_vu_di_kem
from dich_vu_di_kem
join hop_dong_chi_tiet on dich_vu_di_kem.ma_dich_vu_di_kem = hop_dong_chi_tiet.ma_dich_vu_di_kem
join hop_dong on hop_dong_chi_tiet.ma_hop_dong = hop_dong.ma_hop_dong
join khach_hang on hop_dong.ma_khach_hang = khach_hang.ma_khach_hang
join loai_khach on khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
where loai_khach.ten_loai_khach = "Diamond" 
and (khach_hang.dia_chi like "%Vinh" or khach_hang.dia_chi like "%Quảng Ngãi");

-- Task 12.	
-- Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên),
-- ho_ten (khách hàng), so_dien_thoai (khách hàng), ten_dich_vu, 
-- so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem),
-- tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt
-- vào 3 tháng cuối năm 2020 
-- nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.
select hd.ma_hop_dong, nv.ho_ten, kh.ho_ten, kh.so_dien_thoai,
dv.ten_dich_vu,ifnull(sum(hdct.so_luong),0) as so_luong_dich_vu_di_kem, hd.tien_dat_coc
from hop_dong hd
join nhan_vien nv on hd.ma_nhan_vien = nv.ma_nhan_vien
join khach_hang kh on hd.ma_khach_hang = kh.ma_khach_hang
join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
where (hd.ngay_lam_hop_dong between "2020-10-01" and "2020-12-31")
and hd.ma_dich_vu not in 
(select ma_dich_vu 
from hop_dong 
where (hd.ngay_lam_hop_dong between "2021-01-01" and "2021-06-30"))
group by hdct.ma_hop_dong
;

-- Task 13.	
-- Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất
-- bởi các Khách hàng đã đặt phòng.
 select dvdk.ma_dich_vu_di_kem, dvdk.ten_dich_vu_di_kem, sum(hdct.so_luong) as so_lan_su_dung
 from hop_dong_chi_tiet hdct
 join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
 group by hdct.ma_dich_vu_di_kem
 having so_lan_su_dung >= All
 ((select sum(so_luong) 
 from hop_dong_chi_tiet 
 group by ma_dich_vu_di_kem))
 ;
 
 -- Cách 2: Dùng view
drop view if exists so_luong_dich_vu_di_kem;
create view so_luong_dich_vu_di_kem as
select dich_vu_di_kem.*, SUM(hop_dong_chi_tiet.so_luong) as so_lan_su_dung
from dich_vu_di_kem 
join hop_dong_chi_tiet on dich_vu_di_kem.ma_dich_vu_di_kem = hop_dong_chi_tiet.ma_dich_vu_di_kem
group by hop_dong_chi_tiet.ma_dich_vu_di_kem;
select sldvdk.ma_dich_vu_di_kem, sldvdk.ten_dich_vu_di_kem, sldvdk.so_lan_su_dung 
from so_luong_dich_vu_di_kem sldvdk 
where sldvdk.so_lan_su_dung = 
(select MAX(so_luong_dich_vu_di_kem.so_lan_su_dung ) from so_luong_dich_vu_di_kem);
 
-- Task 14.	Hiển thị thông tin các Dịch vụ đi kèm chỉ mới được sử dụng một lần
-- Thông tin hiển thị: ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung
select hd.ma_hop_dong, ldv.ten_loai_dich_vu, dvdk.ten_dich_vu_di_kem, count(hdct.ma_dich_vu_di_kem) as so_lan_su_dung
from hop_dong hd
join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
group by hdct.ma_dich_vu_di_kem
having so_lan_su_dung = 1
order by hd.ma_hop_dong;

-- Task 15.	Hiển thi thông tin của tất cả nhân viên bao gồm
-- ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi 
-- mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.
select nv.ma_nhan_vien, nv.ho_ten, td.ten_trinh_do, bp.ten_bo_phan,
nv.so_dien_thoai, nv.dia_chi, count(hd.ma_nhan_vien) as so_hop_dong_da_ky
from nhan_vien nv
join trinh_do td on nv.ma_trinh_do = td.ma_trinh_do
join bo_phan bp on nv.ma_bo_phan = bp.ma_bo_phan
join hop_dong hd on nv.ma_nhan_vien = hd.ma_nhan_vien
where year(hd.ngay_lam_hop_dong) in(2020,2021)
group by hd.ma_nhan_vien
having so_hop_dong_da_ky <= 3;



