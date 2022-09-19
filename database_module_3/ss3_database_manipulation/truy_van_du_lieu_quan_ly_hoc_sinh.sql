use quan_ly_sinh_vien;

-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’ 
select *
from hoc_sinh
where ten_hoc_sinh like "h%";

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12. 
select *
from lop
where extract(month from ngay_nhap_hoc) = "12";

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select * 
from mon_hoc
where credit between 3 and 5 ;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
SET SQL_SAFE_UPDATES = 0; 
update hoc_sinh
set ma_lop = 2 
where ten_hoc_sinh = "hung";

-- Hiển thị các thông tin: StudentName, Address, Mark. 
-- Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. 
-- nếu trùng sắp theo tên tăng dần.

select hs.ten_hoc_sinh, hs.dia_chi, d.diem
from hoc_sinh hs left join diem d
on hs.ma_hoc_sinh = d.ma_hoc_sinh
order by d.diem desc, hs.ten_hoc_sinh asc;
