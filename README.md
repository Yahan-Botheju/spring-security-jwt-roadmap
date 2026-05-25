
# Spring Security With JWT Roadmap

---

## Project 01 - Stateless JWT Authentication API

---
### 📌 Overview

Stateless JWT Authentication API is a backend REST API project built using **Java**, **Spring Boot**, and **Spring Security**.  
The project demonstrates how to implement a secure authentication and authorization system using **JWT (JSON Web Tokens)** with a completely **stateless architecture**.

---
### 📊 Process

Stateless JWT authentication means the server does not store sessions. After login, it generates a signed JWT token and sends it to the client. The client stores the token and includes it in every request.
For each request, Spring Security extracts and validates the token, loads the user, and sets authentication if it is valid. Access is then controlled based on user roles like ADMIN or USER. After the request is completed, nothing is stored on the server. Every request is independent and must include a valid token.

---

The system manages:

- 👤 User Authentication
- 🔑 JWT Token Generation & Validation
- 🛡️ Role-Based Authorization
- 📋 Task Management
- 🗑️ Soft Delete Support
- 🧩 Clean Architecture Structure

The application follows a layered architecture with clear separation of concerns:

- 🧠 Domain Layer
- ⚙️ Infrastructure Layer
- 🛠️ Use Case Layer
- 🌐 Web Layer

---

### ✨ Features

### 🔥 Core Features

-  Stateless JWT Authentication
-  User Registration & Login
-  Spring Security Integration
-  Role-Based Authorization
-  Task Management System
-  Clean Architecture
-  Soft Delete Support
-  Custom JWT Filter
-  DTO Mapping using MapStruct
-  Request Validation
-  Layered Modular Structure
-  BCrypt Password Encryption
-  Method-Level Security
-  PostgreSQL Integration
-  Swagger/OpenAPI Support

---

### 🧰 Technologies Used

| Technology | Purpose |
|---|---|
|  Java 17 | Main programming language |
|  Spring Boot 4 | Backend framework |
|  Spring Security | Authentication & Authorization |
|  Spring Data JPA | ORM & database operations |
|  PostgreSQL | Relational database |
|  JWT (jjwt) | Token generation & validation |
|  MapStruct | Object mapping |
|  Lombok | Reduce boilerplate code |
|  Spring Web MVC | REST API development |
|  Jakarta Validation | Request validation |
|  Swagger / OpenAPI | API documentation |
|  Hibernate | ORM provider |
|  Gradle | Build tool |

---

### 🧱 Project Architecture

### 🏛️ Clean Architecture

The project follows Clean Architecture principles.

```text
               🌐 Web Layer
         (Controllers, DTOs, APIs)
                     ↓
             🛠️ Use Case Layer
        (Business Application Logic)
                     ↓
              🧠 Domain Layer
         (Core Business Rules)
                     ↑
          ⚙️ Infrastructure Layer
      (Database, Security, Persistence)
```

---

### 📂 Project Structure

```text
stateless_jwt
├── 📁 domain                                        @Core Business Logic & Enterprise Rules
│   ├── 📁 models                                    @Pure Domain Entities & Aggregates
│   │   ├── Role.java                                # User Role Domain Model
│   │   ├── Task.java                                # Task Domain Model
│   │   └── User.java                                # User Domain Model
│   └── 📁 repositories                              @Domain Repository Interfaces (Outbound Ports)
│       ├── TaskRepository.java
│       └── UserRepository.java
│   └── 📁 services                                  @Domain Services (Pure Business Contracts)
│       └── JwtService.java                          # Core Token Operations Interface
│
├── 📁 usecase                                       @Application Specific Business Rules
│   ├── 📁 auth                                      @Inbound Port for Auth Operations
│   │   ├── AuthUseCase.java                         # Feature Interface
│   │   └── AuthUseCaseImpl.java                     # Orchestration of Auth Logic
│   ├── 📁 task                                      @Inbound Port for Task Operations
│   │   ├── TaskUseCase.java
│   │   └── TaskUseCaseImpl.java
│   └── 📁 user                                      @Inbound Port for User Management
│       ├── UserUseCase.java
│       └── UserUseCaseImpl.java
│
├── 📁 infrastructure                                @External Frameworks, Tools & Adapters
│   ├── 📁 auth                                      @Authentication Adapter Configuration
│   │   └── 📁 config                                # Bean Definitions (DI Configuration)
│   │       └── AuthBeanConfig.java
│   ├── 📁 security                                  @Spring Security Configuration & Core Extensions
│   │   ├── 📁 config
│   │   │   ├── ApplicationConfig.java               # Auth Manager, Provider, Password Encoder
│   │   │   ├── CustomUserDetailsBeanConfig.java
│   │   │   ├── JwtBeanConfig.java
│   │   │   └── SecurityConfiguration.java           # SecurityFilterChain & Rule Interceptors
│   │   ├── 📁 filter
│   │   │   └── JwtAuthenticationFilter.java         # Stateless Session Token Interceptor
│   │   ├── 📁 user
│   │   │   ├── CustomUserDetails.java               # Bridge between Domain User and Spring Security User
│   │   │   └── CustomUserDetailsService.java        # Spring Security User Loading Logic
│   │   └── JwtImpl.java                             # Concrete implementation of Domain JwtService
│   ├── 📁 task                                      @Infrastructure Implementation for Task Module
│   │   ├── 📁 config
│   │   │   ├── TaskPersistenceBeanConfig.java
│   │   │   └── TaskUseCaseBeanConfig.java
│   │   └── 📁 persistence                           @Database Layer (PostgreSQL/H2/MySQL)
│   │       ├── 📁 entities
│   │       │   └── TaskEntity.java                  # JPA @Entity Definition
│   │       ├── 📁 jpa
│   │       │   └── JpaTaskRepository.java           # Spring Data JPA Interface
│   │       ├── 📁 mappers
│   │       │   └── TaskPersistenceMapper.java       # Domain Model <-> JPA Entity Mapping
│   │       └── TaskPersistenceImpl.java             # Adapter connecting Domain Repo to JPA Repo
│   └── 📁 user                                      @Infrastructure Implementation for User Module
│       ├── 📁 config
│       │   ├── UserPersistenceBeanConfig.java
│       │   └── UserUseCaseBeanConfig.java
│       └── 📁 persistence
│           ├── 📁 entities
│           │   └── UserEntity.java                  # JPA @Entity Definition
│           ├── 📁 jpa
│           │   └── JpaUserRepository.java           # Spring Data JPA Interface
│           ├── 📁 mappers
│           │   └── UserPersistenceMapper.java       # Domain Model <-> JPA Entity Mapping
│           └── UserPersistenceImpl.java             # Adapter connecting Domain Repo to JPA Repo
│
├── 📁 web                                           @Entry Points & Delivery (UI/API)
│   ├── 📁 auth                                      @Auth Delivery Layer
│   │   ├── 📁 controllers
│   │   │   └── AuthController.java                  # REST API Endpoint (@RestController)
│   │   └── 📁 DTOs                                  @API Request/Response Data Contracts
│   │       ├── AuthRequestDTO.java
│   │       └── AuthResponseDTO.java
│   ├── 📁 task                                      @Task Delivery Layer
│   │   ├── 📁 controllers
│   │   │   └── TaskController.java
│   │   ├── 📁 DTOs
│   │   │   ├── TaskRequestDTO.java
│   │   │   └── TaskResponseDTO.java
│   │   └── 📁 webMappers                            @Web DTO <-> Domain Mapping
│   │       └── TaskWebMapper.java
│   └── 📁 user                                      @User Delivery Layer
│       ├── 📁 controller
│       │   └── UserController.java
│       ├── 📁 DTOs
│       │   ├── UserRequestDTO.java
│       │   └── UserResponseDTO.java
│       └── 📁 webMappers                            @Web DTO <-> Domain Mapping
│           └── UserWebMapper.java
│
└── StatelessJwtApplication.java                     @Spring Boot Main Class
```

---

### 🧠 Domain Models

### 👤 User

Represents application users.

### Fields

- userId
- email
- password
- role

### ⚙️ Business Logic

- Stores encrypted password
- Assigns user role
- Used for authentication & authorization

---

### 📋 Task

Represents user tasks.

### Fields

- taskId
- taskTitle
- taskDescription
- completed
- userId

### ⚙️ Business Logic

- Linked to a specific user
- Tracks completion status

---

### 🏷️ Role Enum

```java
ADMIN
USER
```

---

### 🔐 Authentication System

### 🛡️ Spring Security Configuration

The project uses:

- `SecurityFilterChain`
- `AuthenticationProvider`
- `AuthenticationManager`
- `BCryptPasswordEncoder`

---

### 🔑 JWT Authentication Flow

```text
User Login
    ↓
AuthenticationManager validates credentials
    ↓
JWT Token generated
    ↓
Client stores token
    ↓
Client sends token in Authorization Header
    ↓
JwtAuthenticationFilter validates token
    ↓
Spring Security authenticates request
```

---

### 🎟️ JWT Token System

### 📦 JWT Features

- Token generation
- Token validation
- Username extraction
- Expiration checking
- Stateless authentication

---

### 🔐 JWT Header Format

```http
Authorization: Bearer <jwt-token>
```

---

### 🛡️ Custom Security Components

### 👤 CustomUserDetails

Implements:

```java
UserDetails
```

Responsibilities:

- Provides authenticated user data
- Provides authorities/roles
- Integrates domain user with Spring Security

---

### 🔍 CustomUserDetailsImpl

Implements:

```java
UserDetailsService
```

Responsibilities:

- Loads users from database
- Finds users by email
- Returns `CustomUserDetails`

---

### 🚦 JwtAuthenticationFilter

Extends:

```java
OncePerRequestFilter
```

Responsibilities:

- Reads Authorization header
- Extracts JWT token
- Validates token
- Authenticates user
- Sets SecurityContext

---

### 🗄️ Database Design

### 👤 UserEntity

### Features

- Unique email
- Encrypted password
- Role storage
- One-to-many relationship with tasks
- Soft delete enabled

---

### 📋 TaskEntity

### Features

- Linked to user
- Stores task details
- Completion tracking
- Soft delete enabled

---

### 🗑️ Soft Delete

The project uses Hibernate Soft Delete.

```java
@SoftDelete(columnName = "is_deleted")
```

Used in:

- UserEntity
- TaskEntity

---

### 🔄 Object Mapping

The project uses MapStruct for:

- DTO → Domain
- Domain → Entity
- Entity → Domain
- Update entity mapping

---

### 🌐 REST API Endpoints

### 🔐 Authentication Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/v1/auth/register | Register new user |
| POST | /api/v1/auth/login | Login user |

---

### 👤 User Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/users/profile | Get current user profile |
| PUT | /api/v1/users/profile | Update current user |
| DELETE | /api/v1/users/profile | Delete current user |

---

### 📋 Task Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/tasks | Get all tasks (ADMIN only) |
| POST | /api/v1/tasks | Create task |
| PUT | /api/v1/tasks/{taskId} | Update task |
| DELETE | /api/v1/tasks/{taskId} | Delete task |

---

### 🎭 Role-Based Authorization

### 👥 Supported Roles

```java
ADMIN
USER
```

---

### 🔐 Security Rules

### Public Endpoints

```text
/api/v1/auth/**
```

### Authenticated Endpoints

All other routes require valid JWT authentication.


### 🔒 Password Encryption

Passwords are encrypted using:

```java
BCryptPasswordEncoder
```

Benefits:

- Secure password hashing
- Salt generation
- Protection against rainbow table attacks

---

### 📘 Swagger Documentation

Swagger UI is enabled using SpringDoc OpenAPI.

### 🔗 Swagger Endpoints

```text
/swagger-ui.html
/v3/api-docs
```

---


### 🧠 Key Concepts Implemented

- REST API Development
- JWT Authentication
- Stateless Authentication
- Spring Security
- Clean Architecture
- Repository Pattern
- DTO Pattern
- Dependency Injection
- Layered Architecture
- Role-Based Authorization
- Soft Delete
- Object Mapping
- Validation
- Authentication Filters
- BCrypt Password Hashing

---

### 🚀 Request Flow

```text
Client Request
    ↓
JwtAuthenticationFilter
    ↓
JWT Validation
    ↓
SecurityContext Authentication
    ↓
Controller
    ↓
UseCase Layer
    ↓
Repository Layer
    ↓
Database
```

---

### 🎯 Project Purpose

This project was built to practice:

- 🔐 Stateless - JWT Authentication
- 🛡️ Spring Security
- 🧩 Clean Architecture
- 🏛️ Layered Backend Design
- 📦 DTO & Mapper Usage
- 🗄️ Database Relationships
- 🔒 Stateless Authentication
- 🎭 Role-Based Access Control
- 🧠 Backend Best Practices

---

### 👨‍💻 Author

Developed as a practice project focusing on:

- Secure backend architecture
- Spring Security implementation
- JWT authentication workflow
- Clean & scalable project structure

---

### ⭐ Learning Outcomes

After completing this project, the following concepts were practiced:

- Spring Security Internals
- JWT Authentication Flow
- Authentication Filters
- Security Context Handling
- Repository Abstraction
- DTO Mapping Strategies
- Stateless API Design
- Clean Architecture Separation
- Role-Based Authorization
- JPA Relationships & Persistence

---

## Project 02 - Method-Level Security & Global Security Exceptions

---

### Ongoing project....