create table article (
  id bigserial primary key,
  title text not null,
  trending boolean default false
);