DROP DATABASE IF EXISTS login_data;
CREATE DATABASE login_data;
USE login_data;

drop table if exists users;
create table users (
 id  int(3) NOT NULL AUTO_INCREMENT,
 name varchar(120) NOT NULL,
 email varchar(220) NOT NULL,
 country varchar(120),
 PRIMARY KEY (id)
);

insert into users(name, email, country) values('Trường','minh@codegym.vn','Viet Nam');
insert into users(name, email, country) values('Vinh','kante@che.uk','Kenia');
insert into users(name, email, country) values('Giang','gianghoang@che.uk','Lao');
insert into users(name, email, country) values('Hải','thanhhai@che.uk','Thai');

select * from users
where country like '%%'; 

select * from users
order by 'name';

DELIMITER $$

CREATE PROCEDURE get_user_by_id(IN user_id INT)

BEGIN

    SELECT users.name, users.email, users.country

    FROM users

    where users.id = user_id;

    END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE insert_user(

IN user_name varchar(50),

IN user_email varchar(50),

IN user_country varchar(50)

)

BEGIN

    INSERT INTO users(name, email, country) VALUES(user_name, user_email, user_country);

    END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE get_all_user()

BEGIN

    SELECT *

    FROM users;

    END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE delete_user_by_id(IN user_id INT)

BEGIN

    delete 

    FROM users

    where users.id = user_id;

    END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE update_user(
IN user_id int,

IN user_name varchar(50),

IN user_email varchar(50),

IN user_country varchar(50)

)

BEGIN

    update users
    set 
    name = user_name,
    email = user_email,
    country = user_country
    where id = user_id;

    END$$

DELIMITER ;

