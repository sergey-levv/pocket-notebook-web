CREATE TABLE credential (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	password CHARACTER(40) NOT NULL
);

CREATE TABLE note_type (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    type VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE note (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	title VARCHAR(255) NOT NULL,
	text TEXT,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	pinned BOOLEAN NOT NULL DEFAULT false,
	credential_id BIGINT NOT NULL references credential(id),
	type_id BIGINT NOT NULL references note_type(id)
);

CREATE TABLE task_group (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL
);

CREATE TABLE task (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	title TEXT NOT NULL,
	description TEXT,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	pinned BOOLEAN NOT NULL DEFAULT false,
	execute_at TIMESTAMP,
	notify_at TIMESTAMP,
	note_id BIGINT NOT NULL references note(id),
	group_id BIGINT references task_group(id)
);

CREATE TABLE set_item (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	pinned BOOLEAN NOT NULL DEFAULT false,
	note_id BIGINT NOT NULL references note(id)
);

CREATE INDEX note_credential_id_idx
	ON note(credential_id);

CREATE INDEX note_type_id_idx
    ON note(type_id);
	
CREATE INDEX note_title_idx
	ON note(title);
	
CREATE INDEX task_note_id_idx
	ON task(note_id);
	
CREATE INDEX task_title_idx
	ON task(title);
	
CREATE INDEX task_execute_at_idx
	ON task(execute_at);
	
CREATE INDEX set_item_note_id_idx
	ON set_item(note_id);
	
CREATE INDEX set_item_name_idx
	ON set_item(name);

INSERT INTO note_type (type)
    VALUES ('TEXT');

INSERT INTO note_type (type)
    VALUES ('TASK_LIST');

INSERT INTO note_type (type)
    VALUES ('SET');
