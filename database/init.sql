CREATE TABLE "user" (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	password CHARACTER(40) NOT NULL
);

CREATE TABLE note (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	title VARCHAR(255) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	pinned BOOLEAN NOT NULL DEFAULT false,
	user_id BIGSERIAL NOT NULL references "user"(id)
);

CREATE TABLE note_text (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	text TEXT,
	note_id BIGSERIAL NOT NULL references note(id)
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
	note_id BIGSERIAL NOT NULL references note(id),
	group_id BIGSERIAL references task_group(id)
);

CREATE TABLE set_item (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	pinned BOOLEAN NOT NULL DEFAULT false,
	note_id BIGSERIAL NOT NULL references note(id)
);

CREATE INDEX note_user_id_idx
	ON note(user_id);
	
CREATE INDEX note_title_idx
	ON note(title);
	
CREATE INDEX note_text_note_id_idx
	ON note_text(note_id);
	
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