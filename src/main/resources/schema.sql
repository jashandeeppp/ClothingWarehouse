create table if not exists cloth (
  id identity,   -- for id we have used identity which auto generated id for the fighter so it autoincremented, we don't need to do anything.
  name varchar(50) not null,
  year_of_creation int not null,
  price double  not null,
  brand_from varchar(50) not null,
  created_at timestamp not null
);

-- We name this file schema.sql only because it will execute at the startup because of this name only
-- we can't provide other name to this file.
-- It will execute sql code on start of the app and give us proper setup to do interaction.
