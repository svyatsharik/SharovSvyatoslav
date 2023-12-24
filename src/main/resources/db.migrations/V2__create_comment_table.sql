create table comment (
  id bigserial primary key,
  artid bigint references article(id),
  message text not null
);