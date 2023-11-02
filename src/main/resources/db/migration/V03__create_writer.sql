create table if not exists public.writer
(
    id_writer  bigint not null
        primary key,
    first_name varchar,
    last_name  varchar,
    region_id  bigint
);