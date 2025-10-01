-- ==========================================================
-- VISTAS BASE
-- ==========================================================

-- 1️⃣ Vista de blogs con autor y etiquetas
CREATE OR REPLACE VIEW vista_blogs AS
SELECT 
    b.id AS blog_id,
    b.title AS titulo,
    b.descripcion,
    b.created_at,
    b.usuario_id,
    u.nombre AS autor_nombre,
    u.apellido AS autor_apellido,
    STRING_AGG(e.nombre, ', ') AS etiquetas
FROM blogs b
JOIN usuarios u ON u.id = b.usuario_id
LEFT JOIN etiquetas_blogs eb ON eb.id_blog = b.id
LEFT JOIN etiquetas e ON e.id = eb.id_etiqueta
GROUP BY b.id, u.nombre, u.apellido
ORDER BY b.created_at DESC;

-- 2️⃣ Vista de comentarios por blog
CREATE OR REPLACE VIEW vista_comentarios_blog AS
SELECT 
    c.id AS comentario_id,
    c.descripcion,
    c.created_at,
    c.id_blog,
    c.id_usuario,
    u.nombre AS usuario_nombre,
    u.apellido AS usuario_apellido
FROM comentarios c
JOIN usuarios u ON u.id = c.id_usuario
ORDER BY c.created_at ASC;

-- ==========================================================
-- CONSULTAS SEGMENTADAS
-- ==========================================================

-- 1️⃣ Un usuario puede ver sus blogs publicados
SELECT *
FROM vista_blogs
WHERE usuario_id = :usuario_id
ORDER BY created_at DESC;

-- 3️⃣ Un usuario puede ver los blogs publicados de otro usuario
SELECT *
FROM vista_blogs
WHERE usuario_id = :otro_usuario_id
ORDER BY created_at DESC;

-- 4️⃣ Un usuario puede ver los blogs más recientes de otros usuarios (paginados 20 en 20)
SELECT *
FROM vista_blogs
WHERE usuario_id <> :usuario_id
ORDER BY created_at DESC
LIMIT 20 OFFSET :offset;

-- ==========================================================
-- EJEMPLOS DE PAGINACIÓN DE COMENTARIOS (20 en 20)
-- ==========================================================

-- Un usuario puede ver los comentarios de un blog (paginados 20 en 20)
SELECT *
FROM vista_comentarios_blog
WHERE id_blog = :blog_id
ORDER BY created_at ASC
LIMIT 20 OFFSET 0;  -- primera página

SELECT *
FROM vista_comentarios_blog
WHERE id_blog = :blog_id
ORDER BY created_at ASC
LIMIT 20 OFFSET 20; -- segunda página

SELECT *
FROM vista_comentarios_blog
WHERE id_blog = :blog_id
ORDER BY created_at ASC
LIMIT 20 OFFSET 40; -- tercera página