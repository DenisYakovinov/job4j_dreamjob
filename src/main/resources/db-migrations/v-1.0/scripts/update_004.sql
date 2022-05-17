CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name TEXT,
  email TEXT,
  password TEXT
);
ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);