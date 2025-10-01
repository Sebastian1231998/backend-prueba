-- Tabla Usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Tabla Blogs
CREATE TABLE blogs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    descripcion VARCHAR(1000) NOT NULL,
    usuario_id INT REFERENCES usuarios(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Tabla Etiquetas
CREATE TABLE etiquetas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Relación muchos a muchos entre Blogs y Etiquetas
CREATE TABLE etiquetas_blogs (
    id SERIAL PRIMARY KEY,
    id_blog INT REFERENCES blogs(id),
    id_etiqueta INT REFERENCES etiquetas(id)
);

-- Tabla Comentarios
CREATE TABLE comentarios (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(500) NOT NULL,
    id_blog INT REFERENCES blogs(id),
    id_usuario INT REFERENCES usuarios(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
