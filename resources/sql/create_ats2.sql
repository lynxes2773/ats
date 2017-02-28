CREATE TABLE application_contacts (
	contact_id INTEGER(8) auto_increment PRIMARY KEY,
	application_id INTEGER(8) NOT NULL, 
	FOREIGN KEY fk_app_contacts (application_id) REFERENCES application(application_id),
	contact_name VARCHAR(50) NOT NULL,
	contact_description VARCHAR(255)
);

CREATE TABLE application_attachments (
	attachment_id INTEGER(10) auto_increment PRIMARY KEY,
	application_id INTEGER(8) NOT NULL, 
	FOREIGN KEY fk_app_attach (application_id) REFERENCES application(application_id),
	attachment_filename VARCHAR(255) NOT NULL,
	attachment_type_name VARCHAR(50) NOT NULL,
	attachment_content BLOB
);

CREATE TABLE application_comments (
	comment_id INTEGER(10) auto_increment PRIMARY KEY,
	application_id INTEGER(8) NOT NULL, 
	FOREIGN KEY fk_app_comment (application_id) REFERENCES application(application_id),
	comment_text VARCHAR(255) NOT NULL,
	comment_date DATE NOT NULL
);
