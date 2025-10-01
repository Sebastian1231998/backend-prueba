-- Tabla de Auditoría
CREATE TABLE auditoria (
    id SERIAL PRIMARY KEY,
    tabla_afectada VARCHAR(100) NOT NULL,
    id_registro INT NOT NULL,
    accion VARCHAR(20) NOT NULL, -- INSERT, UPDATE, DELETE
    datos_antes JSONB,
    datos_despues JSONB,
    usuario_id INT REFERENCES usuarios(id),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Ejemplo de trigger para auditoría en la tabla Blogs
CREATE OR REPLACE FUNCTION audit_blogs() RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO auditoria(tabla_afectada, id_registro, accion, datos_despues, usuario_id)
        VALUES('blogs', NEW.id, 'INSERT', row_to_json(NEW), NEW.usuario_id);
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO auditoria(tabla_afectada, id_registro, accion, datos_antes, datos_despues, usuario_id)
        VALUES('blogs', NEW.id, 'UPDATE', row_to_json(OLD), row_to_json(NEW), NEW.usuario_id);
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO auditoria(tabla_afectada, id_registro, accion, datos_antes, usuario_id)
        VALUES('blogs', OLD.id, 'DELETE', row_to_json(OLD), OLD.usuario_id);
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Asociar el trigger a la tabla Blogs
CREATE TRIGGER trigger_audit_blogs
AFTER INSERT OR UPDATE OR DELETE ON blogs
FOR EACH ROW EXECUTE FUNCTION audit_blogs();