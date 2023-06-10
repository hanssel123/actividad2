INSERT INTO customers (address1, address2, city, country, email, name)
VALUES ('Street John Wick, no. 7', 'Casa #25', 'San Diego', 'USA', 'miron.vitold@devias.io', 'Miron Vitold');


-- Orders

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-102', 'CreditCard', 'pending', 500.00, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-101', 'PayPal', 'complete', 324.50, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-100', 'CreditCard', 'canceled', 746.50, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-99', 'PayPal', 'rejected', 56.89, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-98', 'PayPal', 'complete', 541.59, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-97', 'CreditCard', 'pending', 941.21, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-96', 'CreditCard', 'complete', 499.12, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-95', 'PayPal', 'rejected', 588.75, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-94', 'Tarjeta de crédito', 'canceled', 399.99, 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-93', 'PayPal', 'canceled', 500.00, 1);

-- items

INSERT INTO items (currency, name, quantity)
VALUES ('$', 'Project Points', 25);

INSERT INTO items (currency, name, quantity)
VALUES ('$', 'Freelancer Subscription', 1);

-- orders_items

INSERT INTO orders_items (id_Order, id_Item, billing_Cycle, unit_Amount)
VALUES
  (1, 1, 'monthly', 50.25),
  (1, 2, 'monthly', 5.00),
  (2, 1, 'monthly', 50.25),
  (2, 2, 'monthly', 5.00),
  (3, 1, 'monthly', 50.25),
  (3, 2, 'monthly', 5.00),
  (4, 1, 'monthly', 50.25),
  (4, 2, 'monthly', 5.00),
  (5, 1, 'monthly', 50.25),
  (5, 2, 'monthly', 5.00),
  (6, 1, 'monthly', 50.25),
  (6, 2, 'monthly', 5.00),
  (7, 1, 'monthly', 50.25),
  (7, 2, 'monthly', 5.00),
  (8, 1, 'monthly', 50.25),
  (8, 2, 'monthly', 5.00),
  (9, 1, 'monthly', 50.25),
  (9, 2, 'monthly', 5.00),
  (10, 1, 'monthly', 50.25),
  (10, 2, 'monthly', 5.00);