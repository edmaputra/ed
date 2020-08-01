create table employee (id bigint not null,
create_time timestamp,
creator varchar(120),
delete_flag boolean not null,
update_time timestamp,
updater varchar(120),
delete_time timestamp,
delete_by varchar(120),
version varchar(10),
birth_date date not null,
birth_place varchar(100) not null,
email varchar(80),
first_name varchar(120) not null,
gender integer not null,
last_name varchar(120) not null,
marital_status integer not null,
middle_name varchar(120),
phone_number varchar(20) not null,
age integer not null,
primary key (id));
