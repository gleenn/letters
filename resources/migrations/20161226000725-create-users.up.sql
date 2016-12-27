create sequence user_id_seq;
create table users (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    email varchar(20) not null,
    password varchar(20) not null,
    created_at timestamp not null,
    updated_at timestamp not null);
create unique index on users (email);
