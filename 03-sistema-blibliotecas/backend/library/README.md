# backend-prueba

Backend desarrollado con **Spring Boot 3** y **Java 21** para la gestión de bibliotecas, libros, membresías y préstamos.

---

## Requisitos

- **Java 21**
- **Maven 3.8+**
- **PostgreSQL** (o la base de datos que se configure en application.properties)
- **Git** (opcional, para clonar el repositorio)

---

## Clonar el proyecto

git clone <URL_DEL_REPOSITORIO>
cd backend-prueba

---

## Configuración local

1. Crear la base de datos local (ejemplo PostgreSQL):

CREATE DATABASE library_db;

2. Configurar las credenciales en `src/main/resources/application.properties`:

spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

> Opcional: agregar variables de entorno si el proyecto requiere tokens o API keys.

---

## Ejecutar el proyecto

mvn clean install
mvn spring-boot:run

El backend estará disponible en:

http://localhost:8080/

---

## Ejecutar pruebas

mvn test

> Se recomienda usar H2 en memoria para pruebas locales si se necesita aislar la base de datos real.

---

## Funcionalidades del sistema

## 1. Bibliotecas

1. El sistema debe permitir que un usuario vea la información de una biblioteca específica, incluyendo su nombre y otros detalles relevantes.
2. El sistema debe permitir que un usuario vea la lista de todas las bibliotecas disponibles.
3. El sistema debe permitir que un usuario cree una nueva biblioteca, asegurando la validez y unicidad de los datos.
4. El sistema debe permitir que un usuario actualice la información de una biblioteca existente.
5. El sistema debe permitir que un usuario elimine una biblioteca.
6. El sistema debe garantizar que todas las operaciones devuelvan respuestas estructuradas con código de estado HTTP, mensaje y contenido (cuando aplique).

## 2. Libros

1. El sistema debe permitir que un usuario vea la información de un libro específico de una biblioteca, incluyendo detalles y disponibilidad.
2. El sistema debe permitir que un usuario vea una colección de libros agregados más recientemente para una biblioteca.
3. El sistema debe permitir que un usuario cree un nuevo libro en la biblioteca.
4. El sistema debe permitir que un usuario actualice la información de un libro existente.
5. El sistema debe permitir que un usuario elimine un libro de la biblioteca.
6. El sistema debe validar que los datos recibidos para crear o actualizar un libro sean correctos.

## 3. Membresías

1. El sistema debe permitir que un usuario consulte la información de una membresía específica.
2. El sistema debe permitir que un usuario vea la lista de todas las membresías registradas.
3. El sistema debe permitir que un usuario cree una nueva membresía, asegurando que no exista otra activa para la misma biblioteca.
4. El sistema debe permitir que un usuario actualice una membresía existente.
5. El sistema debe permitir que un usuario elimine una membresía.

## 4. Préstamos de libros

1. El sistema debe permitir que un miembro pida prestado un libro de una biblioteca, verificando existencia del libro, membresía activa y disponibilidad.
2. El sistema debe permitir que un miembro devuelva un libro previamente prestado, registrando la fecha de devolución correctamente.
3. El sistema debe permitir que un miembro consulte la lista de todos sus préstamos activos.
4. El sistema debe garantizar que todas las operaciones de préstamo devuelvan respuestas estructuradas con código HTTP, mensaje y datos.
5. El sistema debe manejar correctamente errores como libro no encontrado, préstamo inexistente o membresía inactiva.

---

## Usuario de prueba

1. UUID -> 3f726cb8-e051-410e-8b01-8912ffd4f6a8
