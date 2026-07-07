
<h1 align="center"> Spring Security With JWT Roadmap </h1>

---

This roadmap represents my learning journey with JWT authentication & Spring Security. 
I had already explored JWT, refresh token, token rotation & security concepts in Express.js. 
However, implementing the same concepts in Java Spring Boot using Clean Architecture gave me 
a depper understanding of how authentication & authorization work in enterprise applications.

Through these projects, I focused on understanding the complete security flows rather than 
just making it work. My goal is to keep learning & refining these concepts until I can confidently
design & implement secure applications from scratch. 

 - The Projects listed below showcases the step I followed while learning JWT & Spring Security,
For more details, refer to the README & projects repos.


---

### Projects Roadmap.... 

---
 ### ✅ Project 01 - Stateless JWT Authentication
  - Spring Security
  - Authentication & Authorization Flow
  - UserDetails & UserDetailsService
  - Security Filter Chain
  - Custom JWT Authentication Filter
  - JWT Generation & Validation
  - Role Based Authorisation
  - Clean Architecture Implementation
---

 ### ✅ Project 02 - Method Level Security & Global Security Exception Handling
 - @PreAuthorize & Method Security
 - Custom AuthenticationEntryPoint
 - Unauthorized Access Handling (401)
 - Centralized Security Exception Handling
 - Standardized Security Exception Handling

---
### ✅ Project 03 - Cookie Based JWT Authentication
 - HttpOnly Cookie Architecture
 - SameSite Cookie Protection (Strict)
 - Custom Token Extraction Interface (TokenExtractor)
 - Cookie Management Service (CookieService & HttpCookieProvider)
 - Automatic Browser token Transmission
 - XSS & CSRF Hardening
 - Custom JWT Authentication Filter from Cookies
 - Cookie Clearing on Logout Workflow

---
### ✅ Project 04 -  Refresh Token
- Refresh Token Authentication Flow
- Access Token Auto Renewal
- Access Token & Refresh Token Separation
- Secure Refresh Token Validation
- HttpOnly Cookie-Based Refresh Tokens
- Persistent User Session Management
- Automatic Token Re-Issuing
- Secure Logout with Token Invalidation
- Product CRUD Module
- Clean Architecture Enhancements

---
### ✅ Project 05 - Stateful JWT Authentication with Refresh Token Rotation

- Ongoing Project...

---

<h2 align="center"> Project 01 - Stateless JWT Authentication API</h2>

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

-  User Authentication
-  JWT Token Generation & Validation
-  Role-Based Authorization
-  Task Management
-  Soft Delete Support
-  Clean Architecture Structure

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
│   ├── 📁 repositories                              @Domain Repository Interfaces (Outbound Ports)
│   │   ├── TaskRepository.java
│   │   └── UserRepository.java
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


<h2 align="center"> Project 02 - Method-Level Security & Global Security Exceptions </h2>

---

 #### Project 02 is an enhancement of Project 01 rather than a completely new application.

The Clean Architecture structure, JWT authentication flow, and business domain remain the same.
This version introduces custom authentication exception handling to provide standardized JSON
responses for unauthorized requests and improve the overall security experience.


---

## 🚀 Project V2 Enhancements

### 🔥 What's New in Version 2

Version 2 introduces custom JWT authentication error handling and improved security exception responses.

The goal of this update is to provide consistent JSON error responses whenever a user attempts to access protected resources without a valid JWT token.

---

## 🔐 Custom JWT Authentication Entry Point

A new custom authentication entry point has been introduced:

```java
JwtAuthenticationEntryPoint
```

This component implements Spring Security's:

```java
AuthenticationEntryPoint
```

and is responsible for handling authentication failures before requests reach application controllers.

### ✨ Responsibilities

* Handles requests with missing, invalid, or expired JWT tokens
* Return a standardized JSON error response
* Prevent Spring Security from returning default HTML error pages

---

## 📦 Standardized Unauthorized Response

When authentication fails, the API now returns:

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or missing token, Access Denied",
  "path": "/requested-endpoint"
}
```

### Benefits

* Consistent API responses
* Better frontend integration
* Easier error handling for client applications
* Improved developer experience during debugging

---

## 🛡️ Security Configuration Enhancement

The Security Filter Chain has been updated with custom exception handling:

```java
.exceptionHandling(exception -> exception
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
)
```

### Purpose

This configuration instructs Spring Security to use the custom JWT authentication entry point whenever an unauthenticated user attempts to access a protected endpoint.

Instead of returning Spring Security's default response, the application now returns a clean JSON error structure.

---

## 🔄 Authentication Failure Flow

```text
Client Request
      ↓
JWT Authentication Filter
      ↓
Authentication Failure
      ↓
Spring Security
      ↓
JwtAuthenticationEntryPoint
      ↓
Custom JSON Error Response
      ↓
HTTP 401 Unauthorized
```
---

### 📂 Project Structure - Newly Added Folders only

```text
infrastructure
└── security
     ├── 📁 config
     │    ├── SecurityConfiguration.java            @EntryPoint 
     │    └── ...
     ├── 📁 exception                         
     │    └── JwtAuthenticationEntryPoint.java      @Connect error handler
     └── ...

```
---

## 🎯 Security Improvements

### Before V2

* Default Spring Security authentication errors
* Inconsistent error responses
* Difficult frontend error parsing

### After V2

* Custom authentication exception handling
* Consistent JSON responses
* Better API consumer experience
* Cleaner security architecture
* Improved JWT authentication flow

---

## 📚 Spring Security Concepts Added

Version 2 introduces the following Spring Security concepts:

* AuthenticationEntryPoint
* Custom Security Exception Handling
* JWT Authentication Error Responses
* Unauthorized Request Processing
* Centralized Authentication Failure Management

---

## ⭐ Project Evolution

### Version 1

* JWT Authentication
* JWT Authorization
* Stateless Security
* Role-Based Access Control
* User & Task Management

### Version 2

* All Version 1 Features
* Custom AuthenticationEntryPoint
* Centralized Authentication Failure Handling
* Standardized JSON Error Responses
* Improved API Error Consistency
* Better Frontend Integration
* Enhanced Security Experience

---
## 🎯 Why This Upgrade Matters

In real-world REST APIs, clients expect predictable JSON responses. Without a custom AuthenticationEntryPoint, Spring Security may return default authentication responses that are harder for frontend applications to process.

By introducing a centralized JWT authentication error handler, the API now provides clean, consistent, and client-friendly responses whenever authentication fails.

---

<h2 align="center">Project-03 Cookie Based JWT Auth</h2>

---

## 📌 Overview

Project 03 extends the previous Stateless JWT Authentication implementation by introducing **Cookie-Based Authentication** using **HttpOnly Cookies**.

In Project 01, JWT tokens were stored on the client side and sent through the `Authorization` header for every request. While this approach is commonly used, modern applications often store access tokens inside secure cookies to reduce exposure to client-side JavaScript.

This project demonstrates how to integrate JWT authentication with Spring Security while storing authentication tokens inside **HttpOnly Cookies**, allowing the browser to automatically send tokens with requests.

The project continues to follow **Clean Architecture** principles and maintains a completely **stateless authentication flow**.

---

## 🚀 Project V3 Enhancements

### 🔥 What's New in Version 3

Version 3 introduces Cookie-Based JWT Authentication.

Instead of sending JWT tokens through the Authorization header, tokens are now stored inside secure cookies and automatically included in requests by the browser.

---

## 📊 Authentication Flow

Cookie-based JWT authentication remains stateless.

After a user successfully registers or logs in:

1. A JWT token is generated.
2. The token is stored inside an HttpOnly cookie.
3. The browser automatically sends the cookie with every request.
4. Spring Security extracts the token from the cookie.
5. The JWT is validated.
6. The user is authenticated.
7. Access is granted to protected resources.

---

### 🔄 Cookie-Based Authentication Flow

```text
User Login / Register
        ↓
Generate JWT Token
        ↓
Store JWT in HttpOnly Cookie
        ↓
Browser Automatically Sends Cookie
        ↓
JwtAuthenticationFilter
        ↓
Extract Token From Cookie
        ↓
Validate JWT
        ↓
Authenticate User
        ↓
Access Protected Resources
```

---

## 🍪 HttpOnly Cookie Implementation

A dedicated cookie provider was introduced to manage authentication cookies.

### Responsibilities

* Create authentication cookies
* Read authentication cookies
* Remove authentication cookies during logout
* Extract JWT token from incoming requests

---

## 🔐 Cookie Security Features

The authentication cookie is configured with:

```text
🔐 Cookie Security Features
The authentication cookie is configured with:

- HttpOnly = true (Prevents JavaScript access to JWT tokens, mitigating XSS risks)
- SameSite = Strict (Provides top-tier protection from Cross-Site Request Forgery - CSRF)
- Path = / (Accessible throughout all application context paths)
- MaxAge = 24 Hours
- Secure = false (Set to false for easy Local Development environment validation. Must be set to true in Production environments requiring SSL/TLS).
```

### Benefits

* Prevents JavaScript access to JWT tokens
* Reduces XSS attack risks
* Browser automatically handles token transmission
* No need to manually attach Authorization headers
* Maintains stateless authentication

---

## 🔑 Authentication Endpoints

### Register User

```http
POST /api/v1/auth/register
```

### Features

* Creates new user account
* Encrypts password using BCrypt
* Generates JWT token
* Stores token inside HttpOnly cookie

---

### Login User

```http
POST /api/v1/auth/login
```

### Features

* Validates credentials
* Generates JWT token
* Stores token inside HttpOnly cookie

---

### Logout User

```http
POST /api/v1/auth/logout
```

### Features

* Removes authentication cookie
* Invalidates client authentication state

---

## 📝 Note Management Module

This project introduces a Note Management feature for authenticated users.

### Features

* Create Note
* View Own Notes
* Update Note
* Delete Note
* Ownership Validation

---

### Note Endpoints

| Method | Endpoint               | Description                    |
| ------ | ---------------------- | ------------------------------ |
| GET    | /api/v1/notes          | Get authenticated user's notes |
| POST   | /api/v1/notes          | Create new note                |
| PUT    | /api/v1/notes/{noteId} | Update note                    |
| DELETE | /api/v1/notes/{noteId} | Delete note                    |

---

## 👤 User Profile Management

### Features

* Update User Profile
* Delete User Account

---

### User Endpoints

| Method | Endpoint              | Description         |
| ------ | --------------------- | ------------------- |
| PUT    | /api/v1/users/profile | Update current user |
| DELETE | /api/v1/users/profile | Delete current user |

---

## 🛡️ Spring Security Enhancements

The authentication filter has been updated to extract JWT tokens from cookies instead of Authorization headers.

### New Security Components

#### HttpCookieProvider

Responsibilities:

* Create JWT cookie
* Read JWT cookie
* Delete JWT cookie

---

#### TokenExtractor

Responsibilities:

* Extract JWT token from incoming cookies
* Provide token to authentication filter

---

#### JwtAuthenticationFilter

Responsibilities:

* Read JWT from cookie
* Validate token
* Load user details
* Set SecurityContext

---

## 📂 Project Structure - Structure has Enhanced 

```text

cookie_based_jwt_auth
├── 📁 domain                                        @Core Business Logic & Enterprise Rules (Framework Independent)
│   ├── 📁 models                                    @Pure Domain Entities & Aggregates
│   │   ├── Note.java                                # Note Domain Model (Enterprise Object)
│   │   ├── Role.java                                # User Role Domain Enum / Model
│   │   └── User.java                                # User Domain Model
│   ├── 📁 repositories                              @Domain Repository Interfaces (Outbound Ports)
│   │   ├── NoteRepository.java                      # Note Outbound Contract
│   │   └── UserRepository.java                      # User Outbound Contract
│   └── 📁 services                                  @Domain Services (Pure Business Contracts)
│       └── CookieService.java                       # Core Cookie Token Operations Contract
│
├── 📁 usecase                                       @Application Specific Business Rules
│   ├── 📁 auth                                      @Inbound Port Orchestration for Auth Operations
│   │   ├── AuthUseCase.java                         # Feature Interface
│   │   └── AuthUseCaseImpl.java                     # Registration & Log In/Out Workflow Logic
│   ├── 📁 note                                      @Inbound Port Orchestration for Note Operations
│   │   ├── NoteUseCase.java                         # Feature Interface
│   │   └── NoteUseCaseImpl.java                     # Note Ownership & Management Business Flow
│   └── 📁 user                                      @Inbound Port Orchestration for User Operations
│       ├── UserUseCase.java                         # Feature Interface
│       └── UserUseCaseImpl.java                     # Profile Context Workflow Logic
│
├── 📁 infrastructure                                @External Frameworks, Tools, & Infrastructure Adapters
│   ├── 📁 _configs                                  @Spring Dependency Injection Configuration Modules
│   │   ├── 📁 _persistenceBeanConfig                # Spring Bean Providers for Persistence Layer Adapters
│   │   │   ├── NotePersistenceBeanConfig.java
│   │   │   └── UserPersistenceBeanConfig.java
│   │   └── 📁 _usecaseBeanConfig                    # Spring Bean Providers for Use Case Implementations
│   │       ├── AuthUseCaseBeanConfig.java
│   │       ├── NoteUseCaseBeanConfig.java
│   │       └── UserUseCaseBeanConfig.java
│   ├── 📁 _security                                 @Spring Security Framework Configuration & Extensions
│   │   ├── 📁 config                                # Security Beans & Rule Engines
│   │   │   ├── ApplicationConfig.java               # Auth Manager, Provider, & Password Encoder Configs
│   │   │   ├── CustomUserDetailsBeanConfig.java     # Wiring for UserDetailsService
│   │   │   ├── JwtSecurityKeyConfig.java            # Cryptographic Configuration for Access Token Keys
│   │   │   └── SecurityConfig.java                  # SecurityFilterChain, Session Policy, CORS, & Matchers
│   │   ├── 📁 filter                                # Servlets Interception Layer
│   │   │   └── JwtAuthenticationFilter.java         # Authentication Interceptor checking Context state
│   │   ├── 📁 token_extraction                      # Decoupled Request Processing Core
│   │   │   └── TokenExtractor.java                  # Contract for pulling String raw data from Requests
│   │   └── 📁 user_spring_wrapper                    # Security Identity Mappings
│   │       ├── CustomUserDetails.java               # Bridge between Domain User and Spring UserDetails
│   │       └── CustomUserDetailsService.java        # Spring Security User Loading database Bridge
│   │   └── CookieImpl.java                          # Infrastructure-bound token processing implementation
│   ├── 📁 note                                      @Infrastructure Implementation for Note Module
│   │   └── 📁 persistence                           @Database Storage Adaption Layer (JPA / PostgreSQL)
│   │       ├── 📁 entities                          
│   │       │   └── NoteEntity.java                  # Relational Database Mapping Schema (@Entity)
│   │       ├── 📁 jpa                               
│   │       │   └── JpaNoteRepository.java           # Spring Data JPA Interface
│   │       ├── 📁 persistenceMapper                 # MapStruct / Manual Converter Interface
│   │       │   └── NotePersistenceMapper.java       # Translates Domain Model <-> Database Entities
│   │       └── NoteRepositoryImpl.java              # Outbound Adapter tying Domain Repo to Jpa Repo
│   └── 📁 user                                      @Infrastructure Implementation for User Module
│       └── 📁 persistence                           @Database Storage Adaption Layer (JPA / PostgreSQL)
│           ├── 📁 entities                          
│           │   └── UserEntity.java                  # Relational Database Mapping Schema (@Entity)
│           ├── 📁 jpa                               
│           │   └── JpaUserRepository.java           # Spring Data JPA Interface
│           ├── 📁 persistenceMapper                 # MapStruct / Manual Converter Interface
│           │   └── UserPersistenceMapper.java       # Translates Domain Model <-> Database Entities
│           └── UserRepositoryImpl.java              # Outbound Adapter tying Domain Repo to Jpa Repo
│
├── 📁 web                                           @Entry Points, Transport Delivery Layers, & Web APIs
│   ├── 📁 _shared                                   @Cross-Cutting Delivery Utilities
│   │   └── 📁 services                              
│   │       └── HttpCookieProvider.java              # Manages Response Cookie writing and implements TokenExtractor
│   ├── 📁 auth                                      @Authentication Controller Delivery Module
│   │   ├── 📁 controller                            
│   │   │   └── AuthController.java                  # REST Endpoint (@RestController) exposing Auth routes
│   │   ├── 📁 DTOs                                  @API Serialization Data Contracts
│   │   │   ├── AuthRequestDTO.java                  
│   │   │   └── AuthResponseDTO.java                 
│   │   └── 📁 webMapper                             @MapStruct Web Mapper
│   │       └── AuthWebMapper.java                   # Translates HTTP DTOs <-> Domain Entities/Use Cases
│   ├── 📁 note                                      @Note Management API Delivery Module
│   │   ├── 📁 controller                            
│   │   │   └── NoteController.java                  # REST Endpoint handling CRUD for Secure User Notes
│   │   ├── 📁 DTOs                                  @API Serialization Data Contracts
│   │   │   ├── NoteRequestDTO.java                  
│   │   │   └── NoteResponseDTO.java                 
│   │   └── 📁 webMapper                             @MapStruct Web Mapper
│   │       └── NoteWebMapper.java                   # Translates HTTP DTOs <-> Domain Entities/Use Cases
│   └── 📁 user                                      @User Profile Management API Delivery Module
│       ├── 📁 controller                            
│       │   └── UserController.java                  # REST Endpoint for Profile Operations
│       ├── 📁 DTOs                                  @API Serialization Data Contracts
│       │   ├── UserRequestDTO.java                  
│       │   └── UserResponseDTO.java                 
│       └── 📁 webMapper                             @MapStruct Web Mapper
│           └── UserWebMapper.java                   # Translates HTTP DTOs <-> Domain Entities/Use Cases
│
└── CookieBasedJwtAuthApplication.java               @Spring Boot Main Bootstrap Class

```

---

## 🎯 Security Improvements

### Before - project 01 and 02

* JWT stored on client side
* Client manually sends Authorization header
* Token accessible depending on storage mechanism

### After - project 03

* JWT stored inside HttpOnly Cookie
* Browser automatically sends cookie
* JavaScript cannot directly access token
* Improved protection against XSS attacks
* Cleaner authentication workflow

---

## 📚 Spring Security Concepts Added

Project 3 introduces:

* Cookie-Based JWT Authentication
* HttpOnly Cookies
* SameSite Cookie Protection
* Custom Token Extraction
* Cookie Management Services
* Stateless Authentication with Cookies

---

## ⭐ Project Evolution

### Version 1

* Stateless JWT Authentication
* Spring Security
* Role-Based Authorization
* Clean Architecture

### Version 2

* Custom AuthenticationEntryPoint
* Standardized Security Error Responses
* Centralized Authentication Handling

### Version 3

* Cookie-Based JWT Authentication
* HttpOnly Cookie Storage
* Automatic Browser Authentication
* Secure Token Management
* User Notes Module

---

## 🎯 Why This Upgrade Matters

Many modern web applications use cookies instead of browser storage for authentication tokens.

By introducing HttpOnly cookies, authentication becomes more secure while maintaining a stateless architecture.

This project demonstrates how JWT authentication and Spring Security can be combined with secure cookie handling to create a more production-oriented authentication flow.

---

### ⭐ Learning Outcomes

After completing this project, the following concepts were practiced:

* Cookie-Based Authentication
* HttpOnly Cookies
* Secure JWT Storage
* Cookie Security Attributes
* Spring Security Cookie Authentication
* Stateless Security with Cookies
* Custom Token Extraction
* User Ownership Validation
* Note Management APIs
* Clean Architecture Enhancements

---


<h2 align="center">Project 04 - JWT Authentication with Refresh Tokens</h2>

---

## 📌 Overview

Project 04 extends the previous Cookie-Based JWT Authentication implementation by introducing **Refresh Token Authentication**.

In Project 03, users authenticated using JWT Access Tokens stored inside HttpOnly cookies. While secure, access tokens have a limited lifetime and require users to log in again after expiration.

This project solves that problem by implementing a **Refresh Token mechanism**, allowing users to obtain new Access Tokens without re-authenticating.

The project continues to follow **Clean Architecture**, maintains **Stateless Authentication**, and demonstrates a production-ready JWT authentication workflow.

---

## 🚀 Project V4 Enhancements

### 🔥 What's New in Version 4

Version 4 introduces **Refresh Token Authentication**.

Instead of forcing users to log in whenever an Access Token expires, the application now issues a Refresh Token that can securely generate new Access Tokens.

This creates a smoother authentication experience while maintaining application security.

---

## 📊 Authentication Flow

The authentication process now consists of both **Access Tokens** and **Refresh Tokens**.

After a successful login or registration:

1. User credentials are validated.
2. An Access Token is generated.
3. A Refresh Token is generated.
4. Both tokens are stored inside secure HttpOnly cookies.
5. The browser automatically sends cookies with every request.
6. Spring Security validates the Access Token.
7. If the Access Token expires, the Refresh Token is used to generate a new Access Token.
8. The user continues using the application without logging in again.

---

## 🔄 Refresh Token Authentication Flow

```text
User Login / Register
        ↓
Generate Access Token
        ↓
Generate Refresh Token
        ↓
Store Tokens in HttpOnly Cookies
        ↓
Browser Automatically Sends Cookies
        ↓
Protected API Request
        ↓
Validate Access Token
        ↓
Access Granted

If Access Token Expired
        ↓
Validate Refresh Token
        ↓
Generate New Access Token
        ↓
Update Cookie
        ↓
Continue Authentication
```

---

## 🔐 Refresh Token Implementation

A dedicated Refresh Token module has been introduced to securely manage user sessions.

### Responsibilities

- Generate Refresh Tokens
- Store Refresh Tokens
- Validate Refresh Tokens
- Issue new Access Tokens
- Expire invalid Refresh Tokens

---

## 🍪 Cookie Management

Both authentication tokens are stored inside **HttpOnly Cookies**.

### Cookie Security Features

```text
Access Token Cookie

- HttpOnly = true
- SameSite = Strict
- Path = /
- Secure = false (Development)
- Short Expiration Time

Refresh Token Cookie

- HttpOnly = true
- SameSite = Strict
- Path = /
- Secure = false (Development)
- Longer Expiration Time
```

### Benefits

- Prevents JavaScript access
- Protects against XSS attacks
- Automatic browser cookie handling
- Improved user experience
- Seamless token renewal
- Stateless authentication

---

## 🔑 Authentication Endpoints

### Register User

```http
POST /api/v1/auth/register
```

### Features

- Create new user
- Encrypt password using BCrypt
- Generate Access Token
- Generate Refresh Token
- Store authentication cookies

---

## Login User

```http
POST /api/v1/auth/login
```

### Features

- Validate user credentials
- Generate Access Token
- Generate Refresh Token
- Store cookies

---

## Refresh Access Token

```http
POST /api/v1/auth/refresh-token
```

### Features

- Validate Refresh Token
- Generate new Access Token
- Update authentication cookie
- Maintain user session

---

## Logout User

```http
POST /api/v1/auth/logout
```

### Features

- Remove Access Token cookie
- Remove Refresh Token cookie
- End authenticated session

---

## 📦 Product Management Module

This project introduces a Product Management module for authenticated users.

### Features

- Create Product
- View Products
- Update Product
- Delete Product

---

## Product Endpoints

| Method | Endpoint | Description |
|----------|----------|-------------|
| GET | /api/v1/products | Retrieve all products |
| POST | /api/v1/products | Create a product |
| PUT | /api/v1/products/{id} | Update a product |
| DELETE | /api/v1/products/{id} | Delete a product |

---

## 🛡️ Spring Security Enhancements

The authentication flow has been upgraded to support **Access Tokens** and **Refresh Tokens**.

### New Security Components

#### RefreshTokenService

Responsibilities

- Generate Refresh Tokens
- Validate Refresh Tokens
- Store Refresh Tokens
- Remove expired tokens

---

#### CookieService

Responsibilities

- Create authentication cookies
- Read cookies
- Remove cookies
- Manage Access and Refresh Token cookies

---

#### JwtAuthenticationFilter

Responsibilities

- Extract Access Token
- Validate JWT
- Authenticate user
- Set SecurityContext

---

## 📂 Project Structure

```text
refresh_token_auth
├── 📁 domain                                           @Core Business Logic & Enterprise Rules (Framework Independent)
│   ├── 📁 models                                       @Pure Domain Entities & Aggregates
│   │   ├── Product.java                                # Product Domain Model [cite: 1]
│   │   ├── RefreshToken.java                           # Refresh Token Domain Model [cite: 1]
│   │   ├── Role.java                                   # User Role Domain Enum / Model [cite: 1]
│   │   └── User.java                                   # User Domain Model [cite: 1]
│   ├── 📁 repositories                                 @Domain Repository Interfaces (Outbound Ports)
│   │   ├── ProductRepository.java                      # Product Outbound Contract [cite: 2]
│   │   ├── RefreshTokenRepository.java                 # Refresh Token Outbound Contract [cite: 2]
│   │   └── UserRepository.java                         # User Outbound Contract [cite: 2]
│   └── 📁 services                                     @Domain Services (Pure Business Contracts)
│       ├── CookieService.java                          # Core Cookie Operations Contract [cite: 1]
│       └── TokenService.java                           # Token Generation & Validation Contract [cite: 2]
│
├── 📁 usecase                                          @Application Specific Business Rules
│   ├── 📁 auth                                         @Inbound Port Orchestration for Auth Operations
│   │   ├── AuthUseCase.java                            # Feature Interface [cite: 9]
│   │   └── AuthUseCaseImpl.java                        # Registration & Log In/Out Workflow Logic [cite: 9]
│   └── 📁 product                                      @Inbound Port Orchestration for Product Operations
│       ├── ProductUseCase.java                         # Feature Interface [cite: 9]
│       └── ProductUseCaseImpl.java                     # Product Business Flow Logic [cite: 9]
│
├── 📁 infrastructure                                   @External Frameworks, Tools, & Infrastructure Adapters
│   ├── 📁 _configs                                     @Spring Dependency Injection Configuration Modules
│   │   ├── 📁 _persistenceBeanConfig                   # Spring Bean Providers for Persistence Layer Adapters
│   │   │   ├── ProductPersistenceBeanConfig.java       # Wiring for Product Persistence [cite: 2]
│   │   │   ├── RefreshTokenPersistenceBeanConfig.java  # Wiring for Refresh Token Persistence [cite: 3]
│   │   │   └── UserPersistenceBeanConfig.java          # Wiring for User Persistence [cite: 3]
│   │   └── 📁 _usecaseBeanConfig                       # Spring Bean Providers for Use Case Implementations
│   │       ├── AuthUseCaseBeanConfig.java              # Wiring for Authentication Use Case [cite: 3]
│   │       └── ProductUseCaseBeanConfig.java           # Wiring for Product Use Case [cite: 3]
│   ├── 📁 _security                                    @Spring Security Framework Configuration & Extensions
│   │   ├── 📁 config                                   # Security Beans & Rule Engines
│   │   │   ├── ApplicationConfig.java                  # Auth Manager, Provider, & Password Encoder Configs [cite: 4]
│   │   │   ├── CustomUserDetailsBeanConfig.java        # Wiring for UserDetailsService [cite: 4]
│   │   │   ├── JwtImplConfig.java                      # Configuration for JWT Core Bean [cite: 4]
│   │   │   ├── JwtSecurityKeyConfig.java               # Cryptographic Configuration for Access Token Keys [cite: 4]
│   │   │   └── SecurityConfig.java                     # SecurityFilterChain, Session Policy, & Matchers [cite: 4]
│   │   ├── 📁 filter                                   # Servlets Interception Layer
│   │   │   └── JwtAuthenticationFilter.java            # Authentication Interceptor checking Context state [cite: 5]
│   │   ├── 📁 user_spring_wrapper                      # Security Identity Mappings
│   │   │   ├── CustomUserDetails.java                  # Bridge between Domain User and Spring UserDetails [cite: 5]
│   │   │   └── CustomUserDetailsService.java           # Spring Security User Loading database Bridge [cite: 5]
│   │   └── JwtImpl.java                                # Infrastructure-bound token processing implementation [cite: 5]
│   ├── 📁 product                                      @Infrastructure Implementation for Product Module
│   │   └── 📁 persistence                              @Database Storage Adaption Layer (JPA / PostgreSQL)
│   │       ├── 📁 entities                          
│   │       │   └── ProductEntity.java                  # Relational Database Mapping Schema (@Entity) [cite: 6]
│   │       ├── 📁 jpa                               
│   │       │   └── JpaProductRepository.java           # Spring Data JPA Interface [cite: 6]
│   │       ├── 📁 persistenceMapper                    # MapStruct / Manual Converter Interface
│   │       │   └── ProductPersistenceMapper.java       # Translates Domain Model <-> Database Entities [cite: 6]
│   │       └── ProductRepositoryImpl.java              # Outbound Adapter tying Domain Repo to Jpa Repo [cite: 6]
│   ├── 📁 refreshToken                                 @Infrastructure Implementation for RefreshToken Module
│   │   └── 📁 persistence                              @Database Storage Adaption Layer (JPA / PostgreSQL)
│   │       ├── 📁 entities                          
│   │       │   └── RefreshTokenEntity.java             # Relational Database Mapping Schema (@Entity) [cite: 7]
│   │       ├── 📁 jpa                               
│   │       │   └── JpaRefreshTokenRepository.java      # Spring Data JPA Interface [cite: 7]
│   │       ├── 📁 persistenceMapper                    # MapStruct / Manual Converter Interface
│   │       │   └── RefreshTokenPersistenceMapper.java  # Translates Domain Model <-> Database Entities [cite: 7]
│   │       └── RefreshTokenRepositoryImpl.java         # Outbound Adapter tying Domain Repo to Jpa Repo [cite: 7]
│   └── 📁 user                                         @Infrastructure Implementation for User Module
│       └── 📁 persistence                              @Database Storage Adaption Layer (JPA / PostgreSQL)
│           ├── 📁 entities                          
│           │   └── UserEntity.java                     # Relational Database Mapping Schema (@Entity) [cite: 8]
│           ├── 📁 jpa                               
│           │   └── JpaUserRepository.java              # Spring Data JPA Interface [cite: 8]
│           ├── 📁 persistenceMapper                    # MapStruct / Manual Converter Interface
│           │   └── UserPersistenceMapper.java          # Translates Domain Model <-> Database Entities [cite: 8]
│           └── UserRepositoryImpl.java                 # Outbound Adapter tying Domain Repo to Jpa Repo [cite: 8]
│
├── 📁 web                                              @Entry Points, Transport Delivery Layers, & Web APIs
│   ├── 📁 _shared                                      @Cross-Cutting Delivery Utilities
│   │   └── 📁 services                              
│   │       └── CookieServiceImpl.java                  # Manages Response Cookie writing [cite: 10]
│   ├── 📁 auth                                         @Authentication Controller Delivery Module
│   │   ├── 📁 controller                            
│   │   │   └── AuthController.java                     # REST Endpoint (@RestController) exposing Auth routes [cite: 10]
│   │   ├── 📁 DTOs                                     @API Serialization Data Contracts
│   │   │   ├── AuthRequestDTO.java                     # Request credentials transfer contract [cite: 10]
│   │   │   ├── AuthResponseDTO.java                    # Response authentication payload transfer contract [cite: 11]
│   │   │   └── RefreshTokenResponseDTO.java            # Token refresh payload contract [cite: 11]
│   │   └── 📁 webMapper                                @MapStruct Web Mapper
│   │       └── AuthWebMapper.java                      # Translates HTTP DTOs <-> Domain Entities/Use Cases [cite: 11]
│   └── 📁 product                                      @Product Management API Delivery Module
│       ├── 📁 controller                            
│       │   └── ProductController.java                  # REST Endpoint handling CRUD for Products [cite: 11]
│       ├── 📁 DTOs                                     @API Serialization Data Contracts
│       │   ├── ProductRequestDTO.java                  # Product creation/update input format [cite: 12]
│       │   └── ProductResponseDTO.java                 # Product details output format [cite: 12]
│       └── 📁 webMapper                                @MapStruct Web Mapper
│           └── ProductWebMapper.java                   # Translates HTTP DTOs <-> Domain Entities/Use Cases [cite: 12]
│
└── RefreshTokenApplication.java                     @Spring Boot Main Bootstrap Class [cite: 12]
```

---

## 🎯 Security Improvements

### Previous Projects

- Stateless JWT Authentication
- Cookie-Based Authentication
- HttpOnly Cookies

### Project 04

- Refresh Token Authentication
- Automatic Access Token Renewal
- Persistent User Sessions
- Secure Cookie Storage
- Improved Authentication Flow

---

## 📚 Spring Security Concepts Added

Project 04 introduces:

- Refresh Token Authentication
- Access Token Renewal
- Persistent Login Sessions
- Refresh Token Validation
- Cookie-Based Token Management
- Stateless Authentication
- Clean Architecture

---

## ⭐ Project Evolution

## Version 1

- Stateless JWT Authentication
- Spring Security
- Role-Based Authorization
- Clean Architecture

---

## Version 2

- Custom AuthenticationEntryPoint
- Standardized Security Error Responses
- Centralized Authentication Handling

---

## Version 3

- Cookie-Based JWT Authentication
- HttpOnly Cookies
- Secure Cookie Storage
- User Notes Module

---

## Version 4

- Refresh Token Authentication
- Automatic Access Token Renewal
- Persistent User Sessions
- Cookie-Based Refresh Tokens
- Product Management Module
- Production-Oriented Authentication Flow

---

## 🎯 Why This Upgrade Matters

Modern applications typically use **short-lived Access Tokens** to reduce security risks.

However, requiring users to log in repeatedly creates a poor user experience.

Refresh Tokens solve this problem by securely issuing new Access Tokens whenever necessary.

This project demonstrates how Spring Security, JWT Authentication, Refresh Tokens, and HttpOnly Cookies work together to build a scalable and production-ready authentication system.

---

## ⭐ Learning Outcomes

After completing this project, the following concepts were practiced:

- JWT Authentication
- Refresh Token Authentication
- Access Token Renewal
- Stateless Security
- HttpOnly Cookies
- Spring Security Filters
- Cookie Management
- Refresh Token Validation
- Product CRUD APIs
- Clean Architecture
- Repository Pattern
- Use Case Layer
- DTO Mapping
- Secure Authentication Design

---


<h2 align="center">Project 05 - Stateful JWT & Refresh Token Rotation</h2>

---

### Ongoing project...

---