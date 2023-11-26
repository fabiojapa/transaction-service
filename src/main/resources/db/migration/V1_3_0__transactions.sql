CREATE TABLE transactions (
  transaction_id SERIAL PRIMARY KEY,
  account_id INT UNIQUE NOT NULL,
  operation_type_id INT UNIQUE NOT NULL,
  amount NUMERIC(10, 2) UNIQUE NOT NULL,
  event_date TIMESTAMP DEFAULT NOW(),
  CONSTRAINT fk_account
      FOREIGN KEY(account_id)
          REFERENCES accounts(account_id),
  CONSTRAINT fk_operation_typ
      FOREIGN KEY(operation_type_id)
          REFERENCES operation_types(operation_type_id)
);