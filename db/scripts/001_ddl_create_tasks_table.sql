CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       title text,
                       description TEXT,
                       created TIMESTAMP,
                       done BOOLEAN
);
