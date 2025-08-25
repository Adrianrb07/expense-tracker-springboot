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

## 📑 Endpoints principales
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
👉 [http://localhost:8081/h2-console](http://localhost:8081/h2-console)  
JDBC URL: `jdbc:h2:mem:testdb`

## ✅ Estado actual
- [x] CRUD completo de gastos
- [x] Validaciones y excepciones personalizadas
- [x] Filtros por rango de fechas
- [x] Tests unitarios e integración 
- [X] Documentación con Swagger
- [ ] Dockerfile + despliegue en la nube

## ✨ Objetivo

Proyecto en evolución para reforzar conocimientos en:

- Desarrollo de microservicios con Spring Boot

- Buenas prácticas (DTOs, validación, exceptions, testing)

- DevOps básico (Docker, CI/CD)

- Integración con React (frontend en desarrollo futuro)

---
📌 Desarrollado como parte de un **plan de formación personal** en backend con Spring Boot por [Adrián R.](https://github.com/Adrianrb07)


