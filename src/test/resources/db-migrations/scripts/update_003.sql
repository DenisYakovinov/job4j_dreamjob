CREATE TABLE candidates (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created DATE,
    photo BYTEA
)