-- Índice para consultas por rango de fechas
CREATE INDEX IF NOT EXISTS idx_expense_date ON expense (expense_date);

-- Constraints: Flyway garantiza que solo se ejecutan una vez, no hace falta IF NOT EXISTS
ALTER TABLE expense
  ADD CONSTRAINT chk_expense_amount_positive CHECK (amount_minor > 0);

ALTER TABLE expense
  ADD CONSTRAINT chk_expense_currency_len CHECK (char_length(currency) = 3);