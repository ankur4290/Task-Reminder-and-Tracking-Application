CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    due_date TIMESTAMP,
    completed BOOLEAN
);
