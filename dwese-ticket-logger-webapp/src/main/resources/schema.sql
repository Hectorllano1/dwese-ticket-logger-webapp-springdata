-- Crear tabla para las Comunidades Autónomas de España
CREATE TABLE IF NOT EXISTS regions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    image VARCHAR(255) NULL
);

-- Si la tabla ya existía de antes (volumen de Docker con datos), añade la columna image
ALTER TABLE regions ADD COLUMN IF NOT EXISTS image VARCHAR(255) NULL;

-- Si la tabla ya existía con id INT, lo ampliamos a BIGINT para que coincida con el Long de la entidad
ALTER TABLE regions MODIFY id BIGINT AUTO_INCREMENT;

-- Crear tabla para las provincias españolas (cada una pertenece a una región)
CREATE TABLE IF NOT EXISTS provinces (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    id_region BIGINT NOT NULL,
    FOREIGN KEY (id_region) REFERENCES regions(id)
);
