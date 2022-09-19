use quan_ly_sinh_vien;

-- Hiển thị số lượng sinh viên ở từng nơi
select dia_chi, count(ma_hoc_sinh) as so_luong
from hoc_sinh
group by dia_chi;

-- Tính điểm trung bình các môn học của mỗi học viên
select ten_hoc_sinh, avg(diem) as diem_trung_binh
from hoc_sinh
join diem
on hoc_sinh.ma_hoc_sinh = diem.ma_hoc_sinh
group by diem.ma_hoc_sinh;

-- Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 10
select ten_hoc_sinh, avg(diem) as diem_trung_binh
from hoc_sinh
join diem
on hoc_sinh.ma_hoc_sinh = diem.ma_hoc_sinh
group by diem.ma_hoc_sinh
having  diem_trung_binh > 10;

-- Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
select ten_hoc_sinh, avg(diem) as diem_trung_binh
from hoc_sinh
join diem
on hoc_sinh.ma_hoc_sinh = diem.ma_hoc_sinh
group by diem.ma_hoc_sinh
having diem_trung_binh >= all (select  avg(diem) from diem group by diem.ma_hoc_sinh);

-- drop view if exists v_diem_trung_binh;
-- create view v_diem_trung_binh as
-- select hoc_sinh.ma_hoc_sinh, avg(diem) as diem_trung_binh
-- from hoc_sinh 
-- join diem
-- on hoc_sinh.ma_hoc_sinh = diem.ma_hoc_sinh
-- group by diem.ma_hoc_sinh;
-- ;
-- select hs.ten_hoc_sinh, v_diem_trung_binh.diem_trung_binh
-- from hoc_sinh hs 
-- join v_diem_trung_binh on hs.ma_hoc_sinh = v_diem_trung_binh.ma_hoc_sinh
-- where  v_diem_trung_binh.diem_trung_binh = (select max(diem_trung_binh) from v_diem_trung_binh);
