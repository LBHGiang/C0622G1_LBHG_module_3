use data_furama;
select * from facility where is_delete = 0 and facility_type_id = 1 ;
select  COLUMN_NAME,data_type from information_schema.columns where table_schema = 'data_furama' and table_name = 'facility';
DESCRIBE facility;
select * from facility where id =2;