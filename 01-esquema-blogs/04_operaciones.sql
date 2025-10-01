-- ==========================================================
-- 1️⃣ Publicar un blog (descripción limitada a 1000 caracteres)
-- ==========================================================
INSERT INTO blogs (title, descripcion, usuario_id)
VALUES (:titulo, LEFT(:descripcion, 1000), :usuario_id);

-- ==========================================================
-- 2️⃣ Insertar etiqueta si no existe
-- ==========================================================
INSERT INTO etiquetas (nombre)
VALUES (:nombre_etiqueta)
ON CONFLICT (nombre) DO NOTHING;

-- ==========================================================
-- 3️⃣ Relacionar blog con una o más etiquetas (tabla pivot)
-- ==========================================================
INSERT INTO etiquetas_blogs (id_blog, id_etiqueta)
SELECT :id_blog, e.id
FROM etiquetas e
WHERE e.nombre = :nombre_etiqueta;

-- ==========================================================
-- 4️⃣ Comentar un blog (propio u otros)
-- ==========================================================
INSERT INTO comentarios (descripcion, id_blog, id_usuario)
VALUES (:descripcion, :id_blog, :usuario_id);