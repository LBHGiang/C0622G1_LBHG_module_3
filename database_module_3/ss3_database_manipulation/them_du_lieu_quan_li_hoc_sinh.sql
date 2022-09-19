use quan_ly_sinh_vien;

insert into lop values 
(1,"A1","2008-12-20",1),
(2,"A2","2008-12-22",1),
(3,"B3",current_date(),0);

insert into lop values (3,"B3",current_date(),0);




insert into hoc_sinh values 
(1,"Hung","Ha noi","912113113",1,1),
(2,"Hoa","Hai phong",null,1,1),
(3,"Manh","HCM","123123123",0,2);

insert into mon_hoc values 
(1,"CF",5,1),
(2,"C",6,1),
(3,"HDJ",5,1),
(4,"RDBMS",10,1);

insert into diem values 
(1,1,1,8,1),
(2,1,2,10,2),
(3,2,1,12,1);

SET SQL_SAFE_UPDATES = 0; 
select * from hoc_sinh;
desc diem;

select * from mon_hoc
where trang_thai = true;