drop database if exists sinhvien;
create database sinhvien;
use sinhvien;
create table sinhvienk60(
mssv Int Not null  Primary key  auto_increment ,
ho varchar (50) ,
ten varchar (20) ,
diemthi double
);

select * from sinhvienk60;

DELIMITER $$

DROP PROCEDURE IF EXISTS `sinhvien`.`getTenSV` $$
CREATE PROCEDURE `sinhvien`.`getTenSV` 
   (IN MS_SINHVIEN INT, OUT TEN_SINHVIEN VARCHAR(255))
BEGIN
   SELECT ten INTO TEN_SINHVIEN
   FROM sinhvienk60
   WHERE MSSV = MS_SINHVIEN;
END $$

DELIMITER ;