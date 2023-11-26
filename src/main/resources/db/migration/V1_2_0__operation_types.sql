CREATE TABLE operation_types (
  operation_type_id serial PRIMARY KEY,
  description VARCHAR(50) UNIQUE NOT NULL,
  value int not null
);