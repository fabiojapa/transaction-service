CREATE TABLE operation_type (
  operation_type_id serial PRIMARY KEY,
  description VARCHAR(50) UNIQUE NOT NULL,
  value int not null
);

INSERT INTO operation_type VALUES
    (1, 'COMPRA A VISTA', -1),
    (2, 'COMPRA PARCELADA', -1),
    (3, 'SAQUE', -1),
    (4, 'PAGAMENTO', 1);