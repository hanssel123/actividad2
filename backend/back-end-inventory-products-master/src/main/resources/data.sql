CREATE TABLE products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    currency CHAR(1) NOT NULL,
    image VARCHAR(200),
    inStock BOOLEAN NOT NULL,
    isAvailable BOOLEAN NOT NULL,
    isShippable BOOLEAN NOT NULL,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(20,2) NOT NULL,
    quantity INT NOT NULL,
    sku VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    variants INT NOT NULL
);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('cuidado-salud', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_1.png', true, true, false, 'Cuidado de la salud Erbology', 23.99, 85, '401_1BBXBK', 'publicado', CURRENT_TIMESTAMP(), 2);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('maquillaje', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_2.png', false, false, true, 'Maquillaje Lancome Rouge', 95.00, 0, '978_UBFGJC', 'publicado', CURRENT_TIMESTAMP(), 1);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('joyeria', CURRENT_TIMESTAMP(), '$', NULL, true, true, false, 'Colección de pulseras superpuestas', 155.00, 48, '211_QFEXJO', 'borrador', CURRENT_TIMESTAMP(), 5);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('cuidado-piel', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_4.png', true, false, true, 'Cuidado de la piel Necessaire', 17.99, 5, '321_UWEAJT', 'publicado', CURRENT_TIMESTAMP(), 1);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('cuidado-piel', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_5.png', true, false, true, 'Cuidado de la piel Soja CO', 65.99, 10, '592_LDKDI', 'borrador', CURRENT_TIMESTAMP(), 1);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('maquillaje', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_6.png', true, false, true, 'Maquillaje Barra de labios', 76.99, 22, '324_DKSEKD', 'borrador', CURRENT_TIMESTAMP(), 1);

INSERT INTO products (category, createdAt, currency, image, inStock, isAvailable, isShippable, name, price, quantity, sku, status, updatedAt, variants)
VALUES ('cuidado-salud', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_7.png', true, false, true, 'Cuidado de la salud Ritual', 115.20, 15, '598_DOKEII', 'publicado', CURRENT_TIMESTAMP(), 1);
