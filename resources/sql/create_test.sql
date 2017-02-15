CREATE TABLE candidate (
	candidate_id integer(6) auto_increment primary key, 
	first_name varchar(20) not null,
	last_name varchar(20) not null,
	date_of_birth date,
	gender char(1) not null, 
	email varchar(50) not null,
	phone varchar(12),
	intl_dialing_code varchar(3),
	street_address_1 varchar(255),
	street_address_2 varchar(255),
	city varchar(50),
	state varchar(50),
	zipcode varchar(10),
	country varchar(255),
	status char(1),
	summary varchar(2000)
);

