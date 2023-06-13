INSERT INTO products (category, created_At, currency, image, in_Stock, is_Available, is_Shippable, name, price, quantity, sku, status, updated_At, variants)
VALUES ('cuidado-salud', '2023-05-01', '$', '/mock-images/products/product_1.png', true, true, false, 'Cuidado de la salud Erbology', 23.99, 85, '401_1BBXBK', 'publicado', CURRENT_TIMESTAMP(), 2),
('maquillaje', '2023-05-02', '$', '/mock-images/products/product_2.png', false, false, true, 'Maquillaje Lancome Rouge', 95.00, 0, '978_UBFGJC', 'publicado', CURRENT_TIMESTAMP(), 1),
('joyeria', '2023-05-03', '$', NULL, true, true, false, 'Colección de pulseras superpuestas', 155.00, 48, '211_QFEXJO', 'borrador', CURRENT_TIMESTAMP(), 5),
('cuidado-piel', '2023-05-04', '$', '/mock-images/products/product_4.png', true, false, true, 'Cuidado de la piel Necessaire', 17.99, 5, '321_UWEAJT', 'publicado', CURRENT_TIMESTAMP(), 1),
('cuidado-piel', '2023-05-05', '$', '/mock-images/products/product_5.png', true, false, true, 'Cuidado de la piel Soja CO', 65.99, 10, '592_LDKDI', 'borrador', CURRENT_TIMESTAMP(), 1),
('maquillaje', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_6.png', true, false, true, 'Maquillaje Barra de labios', 76.99, 22, '324_DKSEKD', 'borrador', CURRENT_TIMESTAMP(), 1),
('cuidado-salud', CURRENT_TIMESTAMP(), '$', '/mock-images/products/product_7.png', true, false, true, 'Cuidado de la salud Ritual', 115.20, 15, '598_DOKEII', 'publicado', CURRENT_TIMESTAMP(), 1);
