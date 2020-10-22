CREATE TABLE "user" (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	password CHARACTER(40) NOT NULL
);

CREATE TABLE note (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name TEXT,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
	user_id BIGSERIAL references "user"(id)
);

CREATE TABLE content_type (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	type VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE note_content (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	type_id BIGSERIAL references content_type(id),
	note_id BIGSERIAL references note(id),
	text TEXT,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL
);

CREATE INDEX note_user_id_idx
	ON note (user_id);
	
CREATE INDEX note_content_note_id_idx
	ON note_content (note_id);