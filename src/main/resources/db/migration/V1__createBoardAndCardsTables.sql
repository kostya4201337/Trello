CREATE TABLE boards (
    id bigserial PRIMARY KEY,
    name varchar(32),
    description text,
    created_at timestamp(6),
    updated_at timestamp(6)
);

CREATE TABLE cards (
    id bigserial PRIMARY KEY,
    board_id int references boards(id) on delete cascade,
    name varchar(32),
    description text,
    created_at timestamp(6),
    updated_at timestamp(6)
);

