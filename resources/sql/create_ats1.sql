CREATE TABLE position_type_master (
	position_type_id INTEGER(5) PRIMARY KEY,
	position_type_description VARCHAR(20) NOT NULL
);

CREATE TABLE attachments_type_master (
	attachment_type_id INTEGER(10) PRIMARY KEY,
	attachment_type_name VARCHAR(50) NOT NULL
);

CREATE TABLE job_source_type_master (
	job_source_type_id INTEGER(5) PRIMARY KEY,
	job_source_type_description VARCHAR(20) NOT NULL
);

CREATE TABLE application_status_master (
	application_status_id INTEGER(2) PRIMARY KEY,
	application_status_description VARCHAR(20) NOT NULL
);

CREATE TABLE application (
	application_id INTEGER(8) auto_increment PRIMARY KEY,
	application_date DATE NOT NULL,
	position_name VARCHAR(255) NOT NULL,
	positionid VARCHAR(20),
	job_description VARCHAR(5000),
	end_client VARCHAR(255),
	location VARCHAR(50),
	job_source_name VARCHAR(50) NOT NULL,
	job_source_type_description VARCHAR(20) NOT NULL, 
	position_type_description VARCHAR(20) NOT NULL,
	application_status_description VARCHAR(20) NOT NULL
);


