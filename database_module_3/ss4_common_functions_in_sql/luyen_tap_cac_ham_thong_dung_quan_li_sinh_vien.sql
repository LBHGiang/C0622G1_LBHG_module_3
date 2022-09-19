use quan_ly_sinh_vien;
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select * 
from mon_hoc 
where credit >= (select max(credit) from mon_hoc);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select ten_mon_hoc, diem
from diem
join mon_hoc
on diem.ma_mon_hoc = mon_hoc.ma_mon_hoc
where diem = (select max(diem) from diem);

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên
-- xếp hạng theo thứ tự điểm giảm dần
select hoc_sinh.* , avg(diem) as diem_trung_binh
from hoc_sinh 
join diem
on hoc_sinh.ma_hoc_sinh = diem.ma_hoc_sinh
group by diem.ma_hoc_sinh
order by diem_trung_binh desc;