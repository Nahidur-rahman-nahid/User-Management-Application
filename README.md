# User-Management-Application
A Spring Boot project following **Clean Architecture (by Robert C. Martin)** to manage users and roles with full CRUD support, in-memory H2 database, validation, Swagger UI documentation, and unit-tested use cases.

---

## 🚀 Features

- ✅ Create Users and Roles
- ✅ Assign Roles to Users
- ✅ Retrieve User details along with assigned Roles
- ✅ Clean Architecture: Domain, Application, Infrastructure, Configuration layers
- ✅ H2 In-Memory Database (no setup needed)
- ✅ Validation with meaningful error messages
- ✅ Swagger UI for API exploration
- ✅ Unit tests for Application (Service) Layer

## ⚙️ Technology Stack

- Java 17
- Spring Boot 3.1.5
- Spring Web + JPA
- H2 in-memory database
- Spring Validation
- Lombok
- Swagger (Springdoc OpenAPI 2.1.0)
- JUnit 5 + Mockito


## 🔧 Setup Instructions (Run and Test)

### ✅ Prerequisites

- JDK 17+
- Maven 3.8+

### ▶️ How to Run the Application

```bash
# Clone the repository
git clone https://github.com/your-username/usermanagement-clean-arch.git
cd usermanagement-clean-arch

# Build and run
mvn clean install
mvn spring-boot:run

**How to Run Unit Tests**
Unit tests are written for the application layer (services) using JUnit 5 and Mockito.
To test:
mvn test
All tests are located under:
src/test/java/com/example/usermanagement/application/
