CREATE TABLE users (
 id SERIAL PRIMARY KEY,
 name TEXT,
 email VARCHAR(150),
 password TEXT
);
ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);

