CREATE TABLE accounts (
  account_id serial PRIMARY KEY,
  document_number NUMERIC(11) UNIQUE NOT NULL
);