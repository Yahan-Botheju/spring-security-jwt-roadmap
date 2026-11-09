

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