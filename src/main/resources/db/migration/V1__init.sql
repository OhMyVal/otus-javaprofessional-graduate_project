CREATE SCHEMA IF NOT EXISTS graduate_project_library;

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books (id bigserial PRIMARY KEY, author VARCHAR(255), title VARCHAR(255), CONSTRAINT books_unique UNIQUE (title, author));
INSERT INTO books (author, title) VALUES
('Оруэлл', '1984'),
('Оруэлл', 'Скотный двор'),
('Теккерей', 'Ярмарка тщеславия'),
('Брэдбери', 'Вино из одуванчиков'),
('Браун', 'Ангелы и демоны'),
('Ладлем', 'Личность Борна');

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, first_name VARCHAR(255), e_mail VARCHAR(255), CONSTRAINT users_unique UNIQUE (e_mail));
INSERT INTO users (first_name, e_mail) VALUES
('Alexander', 'alex@mail'),
('Bob', 'bob@mail'),
('Tom', 'tom@mail'),
('Jim', 'jim@mail');

DROP TABLE IF EXISTS books_readers CASCADE;
CREATE TABLE books_readers (book_id bigint, user_id bigint, FOREIGN KEY (book_id) REFERENCES books (id), FOREIGN KEY (user_id) REFERENCES users (id));
INSERT INTO books_readers (book_id, user_id) VALUES
(1, 1),
(2, 1),
(4, 1),
(6, 1),
(3, 2),
(5, 2),
(1, 2),
(2, 3),
(4, 3),
(6, 3),
(4, 4),
(3, 4);
