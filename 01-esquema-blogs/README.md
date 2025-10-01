# Esquema de Base de Datos - Sistema de Blogs

## Descripción
Este diseño corresponde a un sistema básico de blogs con usuarios, etiquetas y comentarios.  
El objetivo es permitir la publicación de blogs, clasificación por etiquetas y discusión mediante comentarios.

Se usó un modelo **relacional (SQL)**, aunque se comenta la posibilidad de usar patrones como **CQRS** para mejorar consultas en sistemas de mayor escala.

---

## 🧑‍💻 Requerimientos Funcionales

1. **Gestión de Blogs**
   - Un usuario puede **ver sus blogs publicados**.  
   - Un usuario puede **ver blogs de otros usuarios**, con carga paginada de **20 en 20**, del más reciente al más antiguo.  
   - Un usuario puede **publicar blogs** con límite de **1000 caracteres** en el campo de descripción.  
   - Un usuario puede **definir una o más etiquetas** para clasificar sus blogs.  

2. **Gestión de Comentarios**
   - Un usuario puede **comentar su propio blog**.  
   - Un usuario puede **comentar blogs de otros usuarios**.  
   - Un usuario puede **ver los comentarios en otros blogs**.  
   - Un usuario puede **ver los comentarios en sus propios blogs**.  

3. **Optimización con Vistas**
   - Vista `vista_comentarios_recientes`: muestra los **20 comentarios más recientes**, junto con el nombre del usuario.  
   - Vista `vista_blogs_recientes`: muestra los **20 blogs más recientes** publicados por diferentes usuarios.  

---

---

## Diagrama ERD

**Entidades:**

- **Usuarios**
  - id (PK)
  - nombre
  - apellido
  - correo
  - created_at, updated_at, deleted_at, created_by, updated_by, deleted_by

- **Blogs**
  - id (PK)
  - title
  - descripcion
  - usuario_id (FK → usuarios.id)
  - created_at, updated_at, deleted_at, created_by, updated_by, deleted_by

- **Etiquetas**
  - id (PK)
  - nombre

- **Etiquetas_Blogs**
  - id (PK)
  - id_blog (FK → blogs.id)
  - id_etiqueta (FK → etiquetas.id)

- **Comentarios**
  - id (PK)
  - descripcion
  - id_blog (FK → blogs.id)
  - id_usuario (FK → usuarios.id)
  - created_at, updated_at, deleted_at, created_by, updated_by, deleted_by

---

## Relaciones

- Un usuario puede tener **0 o muchos blogs**.
- Un blog puede tener **1 o más etiquetas**.
- Un blog puede tener **0 o muchos comentarios**.
- Un usuario puede realizar **0 o muchos comentarios**.

---

## Almacenamiento físico

- Se usa el motor PostgreSQL.
- Base de datos relacional SQL.  
- Índices en las claves foráneas para optimizar joins.
- **Vistas**:
  - `vista_comentarios_recientes`: muestra los 20 comentarios más recientes con nombre del usuario (JOIN + ORDER + LIMIT).
  - `vista_blogs_recientes`: muestra los 20 blogs más recientes publicados por diferentes usuarios.

---

## Tabla de Auditoría

Se incluye una tabla central de auditoría para trazabilidad:

```sql
CREATE TABLE auditoria (
    id BIGSERIAL PRIMARY KEY,
    tabla_afectada VARCHAR(100) NOT NULL,
    id_registro BIGINT NOT NULL,
    accion VARCHAR(20) NOT NULL,
    datos_antes JSONB,
    datos_despues JSONB,
    usuario_id BIGINT REFERENCES usuarios(id),
    ip_origen VARCHAR(50),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);