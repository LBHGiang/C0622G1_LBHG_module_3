use data_furama;
select * from facility where is_delete = 0 and facility_type_id = 1 ;
select  COLUMN_NAME,data_type from information_schema.columns where table_schema = 'data_furama' and table_name = 'facility';
DESCRIBE facility;
select * from facility where facility_type_id =2;
select * from facility where is_delete = 0;
INSERT INTO facility ( `name`, area, cost, max_people, standard_room, description_other_convenience, pool_area, number_of_floors, facility_free,rent_type_id,facility_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

SELECT * FROM data_furama.facility where is_delete=0 and name like "%a%" and cost < 103022000 and facility_type_id in (1,2,3) ;

