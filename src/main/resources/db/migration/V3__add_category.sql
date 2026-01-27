CREATE TABLE category (
  id UUID PRIMARY KEY,
  name VARCHAR(60) NOT NULL UNIQUE
);

ALTER TABLE expense
  ADD COLUMN category_id UUID;

ALTER TABLE expense
  ADD CONSTRAINT fk_expense_category
  FOREIGN KEY (category_id)
  REFERENCES category(id);
