create user test password 'super_safe_password';

create database testDB with owner test;

create schema testLibrary;

create table books (
                       id SERIAL primary key,
                       title varchar(100) not null ,
                       author varchar(100) not null
);

alter table books add release_year int;