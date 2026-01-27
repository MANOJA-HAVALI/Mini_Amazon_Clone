# Mini Amazon Clone

Small Spring Boot project demonstrating user registration, login and JWT-based authentication.

## Overview

- Java 21, Spring Boot 4.x, Spring Security, JPA, MySQL, jjwt 0.12.6
- Endpoints under ` /api/auth/** ` for auth (register/login). Swagger available at `/swagger-ui/`.
- JWT used for stateless auth.

## Prerequisites (Windows)

- Java 21 JDK installed and `JAVA_HOME` set
- Maven (or use the included `mvnw.cmd`)
- MySQL database

## Quick setup

1. Clone repository and open in IntelliJ IDEA (IntelliJ IDEA 2025.2.6.1).
2. Create `src/main/resources/application.properties` (or `application.yml`) with DB and JWT settings (example below).
3. Build and run.

## Example `application.properties`
spring.datasource.url=jdbc:mysql://localhost:3306/mini_amazon spring.datasource.username=root spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-very-long-secret-key-at-least-256-bit jwt.expiration-ms=3600000 jwt.ref-expiration-ms=604800000

Replace `your-very-long-secret-key-at-least-256-bit` with a secure random string (use at least 32 bytes).

## Build & run (Windows)

- Using wrapper:
  - Build: `mvnw.cmd -DskipTests package`
  - Run: `mvnw.cmd spring-boot:run`
- Using Maven:
  - Build: `mvn -DskipTests package`
  - Run jar: `java -jar target/Mini-Amazon-Clone-0.0.1-SNAPSHOT.jar`

## Run in IntelliJ

- Import Maven project, let IDE download dependencies.
- Run the Spring Boot application `com.example.Mini.Amazon.Clone.Application` from the IDE run configuration.

## Endpoints

- Register: `POST /api/auth/register`
- Login: `POST /api/auth/login` (returns JWT token)
- Swagger UI: `/swagger-ui/index.html`

## Make all URLs public (development only)

To allow all requests without authentication (not recommended for production), update your security config:

```java
http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
return http.build();
