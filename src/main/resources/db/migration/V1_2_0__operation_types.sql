CREATE TABLE operation_type (
  operation_type_id serial PRIMARY KEY,
  description VARCHAR(50) UNIQUE NOT NULL,
  value int not null
);