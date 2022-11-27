create table users(
    id uuid not null primary key,
    name varchar(150) not null,
    email varchar(255) not null,
    password varchar(100) not null,
    token varchar(1000),
    created timestamp,
    modified timestamp,
    last_login timestamp,
    is_active boolean
);

create table phones(
    id bigint auto_increment,
    number varchar(12) not null,
    city_code varchar(3) not null,
    country_code varchar(3) not null,
    user_id UUID not null,
    foreign key (user_id) references users(id)
);

insert into users (id, name, email, password, token, created, last_login, is_active) values
('d41cf4a3-e953-4c8a-b647-9640982dcd1d','Admin','admin@nisum.com','$2a$12$rqVqlS0OsQOAtn7jl9D.Fuh8BZ1fdhhlOKgOIseUQHpJX5L11y7pi','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBuaXN1bS5jb20iLCJleHAiOjE2Njk0ODI4OTN9.LpDZpJ19zUUaBJCYMJtdWehKhQ_hGeUr_2lpPKheZoE',current_timestamp,current_timestamp,true);