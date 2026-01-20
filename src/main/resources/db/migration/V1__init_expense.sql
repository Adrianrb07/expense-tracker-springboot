CREATE TABLE IF NOT EXISTS expense (
  id UUID PRIMARY KEY,
  description VARCHAR(160) NOT NULL,
  amount_minor BIGINT NOT NULL,
  currency VARCHAR(3) NOT NULL,
  expense_date DATE NOT NULL
);
