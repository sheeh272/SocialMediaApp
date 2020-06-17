CREATE TABLE posts (
    id UUID NOT NULL PRIMARY KEY,
    author_id UUID NOT NULL,
    contents VARCHAR(100),
    FOREIGN KEY (author_id) REFERENCES users(id)
);
