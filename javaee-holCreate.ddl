create sequence hibernate_sequence start with 1 increment by 1
create table Task (id bigint not null, description varchar(2000) not null, finished boolean, task_name varchar(255) not null, version integer, primary key (id))
