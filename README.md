## Herramientas de Testing
- **JUnit 5**: Framework estándar para pruebas unitarias en Java.
- **Mockito**: Para mockear dependencias (como repositorios o servicios externos).
- **TestContainers** (opcional): Para pruebas de integración con MySQL.
- **Jacoco**: Para medir cobertura de código (>80% recomendado).


# Microservicio de Gestión de Cursos
## Tecnologías utilizadas
- **Spring Boot 3.1.5**
- **Spring Data JPA** (MySQL)
- **Spring HATEOAS** (Hipermedia)
- **Swagger/OpenAPI** (Documentación)
- **JUnit 5 + Mockito** (Pruebas unitarias)

## Endpoints
| Método | Ruta               | Descripción                  |
|--------|--------------------|------------------------------|
| GET    | `/api/cursos`      | Obtener todos los cursos     |
| GET    | `/api/cursos/{id}` | Obtener un curso por ID      |
| POST   | `/api/cursos`      | Crear un nuevo curso         |
| PUT    | `/api/cursos/{id}` | Actualizar un curso          |
| DELETE | `/api/cursos/{id}` | Eliminar un curso            |

## Pruebas
- Ejecutar pruebas con: `mvn test`
- Ver cobertura de código en: `target/site/jacoco/index.html`

## Documentación API
- Acceder a Swagger UI: `http://localhost:3380/swagger-ui.html`

