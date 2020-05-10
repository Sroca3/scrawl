CREATE TABLE City
(
    Id   INT PRIMARY KEY IDENTITY,
    Name varchar(100) NOT NULL UNIQUE
);

INSERT INTO City
VALUES ('Atlanta'),
       ('Baltimore'),
       ('Boston'),
       ('Chicago'),
       ('New York City');