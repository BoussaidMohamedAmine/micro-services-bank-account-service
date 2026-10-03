# Bank Account Service

A RESTful microservice built with **Spring Boot** to manage bank accounts. It exposes a simple CRUD API with DTOs, a dedicated mapper layer and centralized exception handling.

## Features

- Create, read, update and delete bank accounts
- Clean layered architecture: Controller → Service → Repository
- DTOs (request / response) to keep the API separate from the JPA entity
- Mapper component based on Spring's `BeanUtils`
- Auto-generated UUID identifiers
- Custom `AccountNotFoundException` returning a `404 Not Found`

## Tech Stack

- Java 21
- Spring Boot 4.x (Spring Web MVC, Spring Data JPA)
- Hibernate 7
- Lombok
- Maven

## Project Structure

```
com.example.bankaccountservice
├── BankAccountServiceApplication.java
├── dto
│   ├── BankAccountRequestDto.java
│   └── BankAccountResponseDto.java
├── entities
│   └── BankAccount.java
├── enums
│   └── AccountType.java
├── exceptions
│   ├── AccountNotFoundException.java
│
├── mappers
│   └── BankAccountMapper.java
├── repositories
│   └── BankAccountRepository.java
├── service
│   ├── AccountService.java
│   └── AccountServiceImpl.java
└── web
    └── AccountRestController.java
```

> Adjust package names above if your layout differs.

## Getting Started

### Prerequisites

- JDK 21 or later
- Maven 3.9+ (or use the included Maven wrapper)

### Clone and run

```bash
git clone https://github.com/BoussaidMohamedAmine/micro-services-bank-account-service.git
cd bank-account-service
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The service starts on **http://localhost:8081**.

### Configuration

Main settings live in `src/main/resources/application.properties`:

```properties
spring.application.name=bank-account-service
server.port=8081

# Show the exception message in error responses
server.error.include-message=always
```

Configure your datasource (`spring.datasource.*`) and JPA settings (`spring.jpa.hibernate.ddl-auto`) according to the database you use.

## API Endpoints

Base URL: `http://localhost:8081`

| Method | Endpoint | Description | Success | Errors |
|--------|----------|-------------|---------|--------|
| `GET` | `/bankAccounts` | List all accounts | `200 OK` | |
| `GET` | `/bankAccounts/{id}` | Get one account | `200 OK` | `404` if not found |
| `POST` | `/bankAccounts` | Create an account | `200 OK` | |
| `PUT` | `/bankAccounts/{id}` | Update an account | `200 OK` | `404` if not found |
| `DELETE` | `/bankAccounts/{id}` | Delete an account | `204 No Content` | `404` if not found |

### Account types

`AccountType` is an enum, for example `CURRENT_ACCOUNT` and `SAVING_ACCOUNT`.

### Create an account

`POST /bankAccounts`

```json
{
  "balance": 5000.0,
  "currency": "MAD",
  "type": "SAVING_ACCOUNT"
}
```

Response:

```json
{
  "id": "c6da415e-33eb-4707-8565-1bc58fb7e558",
  "createAt": "2026-10-03T08:53:32.049Z",
  "balance": 5000.0,
  "currency": "MAD",
  "type": "SAVING_ACCOUNT"
}
```

### Update an account

`PUT /bankAccounts/{id}`

Only the fields you send are changed. Fields that are omitted (or `null`) keep their current value. The `id` and `createAt` fields can never be modified.

```json
{
  "balance": 1000000.0
}
```


### Error response

Requesting an unknown id returns:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Bank account with id 123 not found"
}
```
project is licensed under the MIT License. See the `LICENSE` file for details.


## API Documentation (Swagger UI)

Once the application is running, the interactive documentation is available at:

http://localhost:8081/swagger-ui/index.html

The OpenAPI JSON is available at http://localhost:8081/v3/api-docs.