DROP TABLE IF EXISTS PUBLIC."project";
CREATE TABLE PUBLIC."project"
(
	"id"					SERIAL PRIMARY KEY,
	"title"					VARCHAR(64),
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."task";
CREATE TABLE PUBLIC."task"
(
	"id"					SERIAL PRIMARY KEY,
	"title"					VARCHAR(64),
	"project"				INT4,
	"asign"					INT4,
	"attach"				INT4,
	"board"					INT4,
	"priority"				VARCHAR(64),
	"status"				VARCHAR(64),
	"due_date"				TIMESTAMP,
	"original_estimate"		INT4,
	"decription"			TEXT,
	"start_date"			TIMESTAMP,
	"end_date"				TIMESTAMP,
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."user";
CREATE TABLE PUBLIC."user"
(
	"id"					SERIAL PRIMARY KEY,
	"email"					VARCHAR(64),
	"password"				VARCHAR(64),
	"avatar"				INT4,
	"name"					VARCHAR(64),
	"role"					VARCHAR(64),
	"status"				VARCHAR(64),
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."board";
CREATE TABLE PUBLIC."board"
(
	"id"					SERIAL PRIMARY KEY,
	"title"					VARCHAR(64),
	"project"				INT4,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."comment";
CREATE TABLE PUBLIC."comment"
(
	"id"					SERIAL PRIMARY KEY,
	"message"				TEXT,
	"task"					INT4,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."attach";
CREATE TABLE PUBLIC."attach"
(
	"id"					SERIAL PRIMARY KEY,
	"name"					VARCHAR(64),
	"caption"				VARCHAR(64),
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."notification";
CREATE TABLE PUBLIC."notification"
(
	"id"					SERIAL PRIMARY KEY,
	"message"				INT4,
	"to"					INT4,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);