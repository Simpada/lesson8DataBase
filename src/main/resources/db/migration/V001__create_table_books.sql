create table books (
       id int identity primary key,
       title varchar(100) not null ,
       author varchar(100) not null
);

alter table books add release_year int;