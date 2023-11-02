create table if not exists public.post
(
    id_post   bigint not null
        primary key,
    content   varchar,
    created   bigint,
    updated   bigint,
    idx       integer,
    writer_id bigint
);