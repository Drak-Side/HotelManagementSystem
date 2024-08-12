create database hotel;
use hotel;
create table login(username varchar(20), password varchar(25));
insert into login values ('laxshmi','123');
select * from login;

 drop table employe;
 CREATE TABLE employe (
    name VARCHAR(50),
    age INT,
    gender VARCHAR(10),
    job VARCHAR(30),
    salary DECIMAL(10,2),
    phone CHAR(10),
    aadhar CHAR(12),
    email VARCHAR(50)
);
CREATE TABLE room (
    room VARCHAR(20),       
    available VARCHAR(10),  
    status VARCHAR(20),     
    price DECIMAL(10, 2),   
    type VARCHAR(20)        
);

show tables;
select * from employe;
describe room;
select * from customer;

CREATE TABLE driver (
    name VARCHAR(50),
    age INT,
    gender VARCHAR(10),
    company VARCHAR(50),
    brand VARCHAR(50),
    available VARCHAR(10),
    location VARCHAR(100)
);

CREATE TABLE customer (
    id_type VARCHAR(20),
    id_number VARCHAR(50),
    name VARCHAR(50),
    gender VARCHAR(10),
    country VARCHAR(50),
    room_number VARCHAR(10),
    checked_in BOOLEAN,
    deposit DECIMAL(10, 2)
);
DESCRIBE room;

