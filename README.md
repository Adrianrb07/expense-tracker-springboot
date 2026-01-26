[![CI](https://github.com/Adrianrb07/expense-tracker-springboot/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/Adrianrb07/expense-tracker-springboot/actions/workflows/ci.yml)

# 💸 Expense Tracker – Spring Boot

Microservicio REST para gestión de gastos, desarrollado como **pet project de aprendizaje** en Spring Boot.  
Incluye CRUD completo, validaciones, manejo de errores y persistencia con H2.

## 🚀 Tecnologías
- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 Database (en memoria / consola web)
- Gradle
- JUnit 5 + Mockito (tests)

## 📂 Estructura
```plaintext
src/main/java/dev/adrian/expense_tracker
├── domain → Entidades de negocio (Expense)
├── dto → Request/Response DTOs
├── repository → Interfaces JPA
├── service → Lógica de negocio
├── web
│ ├── ExpenseController → Endpoints REST
│ └── errors → Manejo de excepciones
```

## 📑 Endpoints principales (Expense es la única entidad por ahora)
### Crear gasto
```http
POST /api/expenses
Content-Type: application/json
{
  "description": "Cine",
  "amountMinor": 1500,
  "currency": "EUR",
  "expenseDate": "2025-08-21"
}
```

### Listar todos los gastos
```http
GET /api/expenses
```

### Obtener gasto por ID
```http
GET /api/expenses/{id}
```

### Filtrar por rango de fechas
```http
GET /api/expenses?from=2025-08-01&to=2025-08-31
```

### Actualizar gasto
```http
PUT /api/expenses/{id}
Content-Type: application/json
{
  "description": "Cine",
  "amountMinor": 2000,
  "currency": "EUR",
  "expenseDate": "2025-08-21"
}
```

### Eliminar gasto
```http
DELETE /api/expenses/{id}
```

## 🛠️ Cómo ejecutar
```bash
./gradlew bootRun
```

## 🛢️ Consola H2
Disponible en:  
👉 [http://localhost:8081/h2-console](http://localhost:8081/h2/)  
- JDBC URL: `jdbc:h2:file:./data/expensetracker`
- User Name: `sa`
- Password: (vacío)

## 📘 Documentación de la API (Swagger / OpenAPI)

Este proyecto incluye documentación interactiva generada automáticamente con [springdoc-openapi](https://springdoc.org/).

- **Swagger UI** → [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Esquema OpenAPI (JSON)** → [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

Desde la interfaz Swagger puedes:
- Navegar por los endpoints (`GET`, `POST`, `PUT`, `DELETE`).
- Probar llamadas en vivo desde el navegador.
- Ver ejemplos de request/response.

## 🐘 Docker Compose con Postgres

El proyecto puede ejecutarse utilizando una base de datos **Postgres real** mediante Docker Compose, simulando un entorno cercano a producción.

### ▶️ Arrancar el stack con Postgres
```bash
docker compose --profile pg up --build
```

Este comando levantará:

- La aplicación Spring Boot

- Una base de datos Postgres en contenedor

- Persistencia de datos mediante un volumen Docker

🌐 Servicios disponibles

- API REST → http://localhost:8080/api/expenses

- Swagger UI → http://localhost:8080/swagger-ui/index.html

💾 Persistencia de datos

- Los datos se almacenan en un volumen Docker llamado pgdata.

- Si se detienen y vuelven a levantar los contenedores, los datos no se pierden.

Para resetear completamente la base de datos:
```bash
docker compose --profile pg down -v
```
🔧 Configuración utilizada

- Perfil activo de Spring: docker-pg

- Base de datos: Postgres

- Puerto Postgres: 5432

- Puerto aplicación: 8080

Este modo de ejecución es el recomendado para pruebas de integración y validación del backend.

## 🛫 Migraciones de base de datos (Flyway)

El esquema de la base de datos se gestiona mediante **Flyway**.

- Las migraciones viven en `src/main/resources/db/migration`
- Se aplican automáticamente al arrancar la aplicación
- Ejemplo:
    - `V1__init_expense.sql`
    - `V2__add_indexes_and_constraints.sql`


## ✅ Estado actual
- [x] CRUD completo de gastos
- [x] Validaciones y excepciones personalizadas
- [x] Filtros por rango de fechas
- [x] Tests unitarios e integración 
- [X] Documentación con Swagger
- [X] Dockerización completa
- [X] Soporte para Postgres con Docker Compose
- [X] Flyway para migraciones de BD

## ✨ Objetivo

Proyecto en evolución para reforzar conocimientos en:

- Desarrollo de microservicios con Spring Boot

- Buenas prácticas (DTOs, validación, exceptions, testing)

- DevOps básico (Docker, CI/CD)

- Integración con React (frontend en desarrollo futuro)

---
📌 Desarrollado como parte de un **plan de formación personal** en backend con Spring Boot por [Adrián R.](https://github.com/Adrianrb07)


