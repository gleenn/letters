create table users (
    id integer not null,
    name string not null,
    password string not null,
    created_at timestamp not null,
    updated_at timestamp not null);
create unique index on users (email);
