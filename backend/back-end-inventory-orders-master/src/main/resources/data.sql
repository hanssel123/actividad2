-- Clientes

INSERT INTO customers (address1, address2, city, country, email, name)
VALUES ('Street John Wick, no. 7', 'Casa #25', 'San Diego', 'USA', 'miron.vitold@devias.io', 'Miron Vitold');

INSERT INTO customers (address1, address2, city, country, email, name)
VALUES ('Street Lincoln, no. 4', 'Casa #30', 'California', 'USA', 'juan.perez@devias.io', 'Juan Perez');


-- Pedidos

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, review, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-102', 'CreditCard', 'pending', 500.00, 'good', 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, review, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-101', 'PayPal', 'complete', 324.50, 'regular', 1);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, review, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-100', 'CreditCard', 'canceled', 746.50, 'bad', 2);

INSERT INTO orders (created_At, currency, number, payment_Method, status, total_Amount, review, id_Customer)
VALUES (CURRENT_TIMESTAMP, '$', 'DEV-99', 'PayPal', 'rejected', 56.89, 'good', 2);

-- productos registrados en pedidos

INSERT INTO products (id, category, currency, image, name, price)
VALUES
(1, 'cuidado-salud', '$', '/mock-images/products/product_1.png', 'Cuidado de la salud Erbology', 23.99),
(2, 'maquillaje', '$', '/mock-images/products/product_2.png', 'Maquillaje Lancome Rouge', 95.00),
(3, 'joyeria', '$', NULL, 'Colección de pulseras superpuestas', 155.00);

-- detalles de pedidos y productos

INSERT INTO orders_products(id_Order, id_Product, quantity)
VALUES
(1, 1, 2),
(1, 2, 3),
(2, 1, 1),
(2, 3, 2),
(3, 1, 1),
(3, 2, 3),
(4, 3, 2);
