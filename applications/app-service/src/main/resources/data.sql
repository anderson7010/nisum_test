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