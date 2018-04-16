/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS solutions;
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS texts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    email TEXT PRIMARY KEY NOT NULL,
    role TEXT NOT NULL,
    name TEXT NOT NULL,
    CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT role_not_empty CHECK (role <> ''),
	CONSTRAINT name_not_empty CHECK (name <> '')
);

CREATE TABLE assignments (
    id SERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT,
    grade INTEGER,
    is_complete BOOLEAN default false,
    is_corrected BOOLEAN default false,
    is_published BOOLEAN default false,
    max_score INTEGER NOT NULL
);

CREATE TABLE texts (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    is_published BOOLEAN NOT NULL
);

CREATE TABLE attendance (
    date DATE NOT NULL,
    user_email TEXT,
    FOREIGN KEY (user_email) REFERENCES users(email)
);

CREATE TABLE solutions (
    id SERIAL NOT NULL,
    user_email TEXT NOT NULL,
    assignment_id INTEGER NOT NULL,
    result INTEGER,
    FOREIGN KEY (user_email) REFERENCES users(email),
    FOREIGN KEY (assignment_id) REFERENCES assignments(id)
);

INSERT INTO users (email, role, name) VALUES
	('laci@gmail.ch','mentor', 'laci'), -- 1
	('peter@gmail.ch','student', 'peti'), -- 2
	('lili@live.it', 'student', 'lili'); -- 3

INSERT INTO texts (title, content, is_published) VALUES
	('Hello World is Python','default contnent', true),   -- 1
	('Conditional Statements or Selection Statements', 'default content', false),  -- 2
	('Functions or Methods', 'default content', false); -- 3


INSERT INTO assignments (question, max_score, is_published) VALUES
	('How to create loops?', 20, true),           -- 1
	('Are you OK?', 20, false),           -- 2
	('Do you like Pandas?', 10, false);          -- 3
	     -- 5

INSERT INTO solutions (assignment_id, user_email) VALUES
    (1, 'peter@gmail.ch'), -- 1
    (1, 'lili@live.it');  -- 2
