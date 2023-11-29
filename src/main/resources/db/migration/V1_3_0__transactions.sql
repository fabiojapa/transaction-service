CREATE TABLE transaction (
  transaction_id SERIAL PRIMARY KEY,
  account_id INT NOT NULL,
  operation_type_id INT NOT NULL,
  amount NUMERIC(10, 2) NOT NULL,
  event_date TIMESTAMP DEFAULT NOW() NOT NULL,
  CONSTRAINT fk_account
      FOREIGN KEY(account_id)
          REFERENCES account(account_id),
  CONSTRAINT fk_operation_typ
      FOREIGN KEY(operation_type_id)
          REFERENCES operation_type(operation_type_id)
);