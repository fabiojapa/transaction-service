CREATE TABLE account (
  account_id serial PRIMARY KEY,
  document_number VARCHAR(11) UNIQUE NOT NULL
);