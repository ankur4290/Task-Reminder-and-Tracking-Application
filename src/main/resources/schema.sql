CREATE TABLE tasks (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(500),
    due_date TIMESTAMP,
    completed BOOLEAN,
    email VARCHAR(255)
);
