CREATE TABLE productos (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   price DECIMAL(10, 2) NOT NULL,
   description TEXT,
   imageUrl VARCHAR(255),
   stock INT NOT NULL
);

CREATE TYPE status_enum AS ENUM ('Creada', 'Editada', 'Cancelada');

CREATE TABLE ordenes_compras (
    id BIGSERIAL PRIMARY KEY,
    total DECIMAL(10, 2) NOT NULL,
    status status_enum DEFAULT 'Creada',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Ordenes_Compras_Productos (
   id BIGSERIAL PRIMARY KEY,
   orden_compra_id BIGINT NOT NULL,
   producto_id BIGINT NOT NULL,
   quantity BIGINT NOT NULL,
   price DECIMAL(10, 2) NOT NULL,
   FOREIGN KEY (orden_compra_id) REFERENCES Ordenes_Compras(id),
   FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

CREATE TABLE Carritos (
  id BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Carrito_Productos (
   id BIGSERIAL PRIMARY KEY,
   carrito_id BIGINT NOT NULL,
   producto_id BIGINT NOT NULL,
   quantity INT NOT NULL,
   FOREIGN KEY (carrito_id) REFERENCES Carritos(id),
   FOREIGN KEY (producto_id) REFERENCES Productos(id)
);