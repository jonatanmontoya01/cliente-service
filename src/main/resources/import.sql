-- 1. Primero insertamos las regiones
INSERT INTO regiones (nombre) VALUES ('Suramerica');
INSERT INTO regiones (nombre) VALUES ('Centroamerica');
INSERT INTO regiones (nombre) VALUES ('Norteamerica');
INSERT INTO regiones (nombre) VALUES ('Europa');
INSERT INTO regiones (nombre) VALUES ('Asia');
INSERT INTO regiones (nombre) VALUES ('Africa');
INSERT INTO regiones (nombre) VALUES ('Oceania');
INSERT INTO regiones (nombre) VALUES ('Antartida');

-- 2. Luego insertamos todos los clientes con create_at = CURRENT_DATE
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Linus', 'Torvalds', 'linus@linux.org', CURRENT_DATE, 'linus.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('James', 'Gosling', 'james@java.com', CURRENT_DATE, 'james.jpg', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Dennis', 'Ritchie', 'dennis@bell.com', CURRENT_DATE, 'dennis.jpg', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Grace', 'Hopper', 'grace@navy.mil', CURRENT_DATE, 'grace.jpg', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Ada', 'Lovelace', 'ada@babbage.io', CURRENT_DATE, 'ada.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Alan', 'Turing', 'alan@bletchley.uk', CURRENT_DATE, 'alan.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Margaret', 'Hamilton', 'margaret@nasa.gov', CURRENT_DATE, 'margaret.jpg', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Tim', 'Berners-Lee', 'tim@w3.org', CURRENT_DATE, 'tim.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Guido', 'van Rossum', 'guido@python.org', CURRENT_DATE, 'guido.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Ken', 'Thompson', 'ken@bell.com', CURRENT_DATE, 'ken.jpg', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Bjarne', 'Stroustrup', 'bjarne@cpp.org', CURRENT_DATE, 'bjarne.jpg', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, foto, region_id) VALUES ('Brian', 'Kernighan', 'brian@bell.com', CURRENT_DATE, 'brian.jpg', 3);