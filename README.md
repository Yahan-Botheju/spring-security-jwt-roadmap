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
### [✅ Project 01 - Stateless JWT Authentication](./PROJECT-1.md)
- Spring Security
- Authentication & Authorization Flow
- UserDetails & UserDetailsService
- Security Filter Chain
- Custom JWT Authentication Filter
- JWT Generation & Validation
- Role Based Authorisation
- Clean Architecture Implementation
---

### [✅ Project 02 - Method Level Security & Global Security Exception Handling](./PROJECT-2.md)
- @PreAuthorize & Method Security
- Custom AuthenticationEntryPoint
- Unauthorized Access Handling (401)
- Centralized Security Exception Handling
- Standardized Security Exception Handling

---
### [✅ Project 03 - Cookie Based JWT Authentication](./PROJECT-3.md)
- HttpOnly Cookie Architecture
- SameSite Cookie Protection (Strict)
- Custom Token Extraction Interface (TokenExtractor)
- Cookie Management Service (CookieService & HttpCookieProvider)
- Automatic Browser token Transmission
- XSS & CSRF Hardening
- Custom JWT Authentication Filter from Cookies
- Cookie Clearing on Logout Workflow

---
### [✅ Project 04 -  Refresh Token](./PROJECT-4.md)
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
### [✅ Project 05 - Stateful JWT Authentication with Refresh Token Rotation](./PROJECT-5.md)

- Stateful Refresh Token Authentication
- Database Stored Refresh Tokens
- Refresh Token Rotation
- Refresh Token Replay Detection
- Token Revocation Strategy
- HttpOnly Cookie-Based Refresh Tokens
- JWT Access Token Authentication
- Secure Access Token Renewal
- Protected Wallet Management APIs
- Automatic Wallet Provisioning
- Custom Cookie Management Service
- Spring Security AuthenticationManager
- Custom JWT Authentication Filter
- Repository Pattern & Clean Architecture Enhancements
---
