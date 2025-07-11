
# Employee Attendance Management System API

This is a **REST API** built with **Spring Boot** for managing employee attendance within a company.

---

## 🚀 Technologies Used

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- JWT (JSON Web Tokens)  
- PostgreSQL *(can be replaced using JPA abstraction)*  

---

## ✨ Features

- User registration and authentication with encrypted passwords
- One-to-one relation between `User` and `Employee`
- Employee personal data registration (name, email, position)
- Daily attendance registration (entry and departure times)
- Query attendance by employee, date, or date range
- Secure endpoints based on roles (`ROLE_ADMIN`, `ROLE_EMPLOYEE`)
- Prevents duplicate users and supports logical deletion (disable user)
- Validations to prevent inconsistent or duplicate records
- Fully layered architecture: models, repositories, services, controllers
- JWT-based stateless authentication

---

## 📂 Project Structure

```
employeeasistancemanagement/
├── src/main/java/com/employeeasistance/
│   ├── config/
│   ├── controllers/
│   ├── dtos/
│   ├── enums/
│   ├── models/
│   ├── repositories/
│   └── services/
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## 🧩 Data Models

### `User`
- `UUID id`
- `String username`
- `String password`
- `UserRoles role` (`ROLE_ADMIN`, `ROLE_EMPLOYEE`)
- `Employee employee` (OneToOne)

### `Employee`
- `UUID id`
- `String name`
- `String email`
- `EmployeePosition position` (`EMPLOYEE`, `SUPERVISOR`, `COORDINATOR`, `MANAGER`, `DIRECTOR`)
- `List<Assistance> employeeAssistances` (OneToMany)
- `User user` (OneToOne)

### `Assistance`
- `UUID id`
- `Employee employee` (ManyToOne)
- `LocalDate date`
- `LocalTime entryTime`
- `LocalTime departureTime`

---

## 🔐 Authentication and Roles

- **JWT** is used for secure, stateless authentication.
- Roles are enforced per endpoint via annotations.
- Each user must first register and then complete their `Employee` profile.
- Admins can manage all records; employees can only view and submit their own.

---

## 📡 API Endpoints

### 🔐 `AuthController`
- `POST /auth/login` – Log in and obtain JWT token

---

### 👤 `UserController`
- `GET /user` – Get all users
- `GET /user/{id}` – Get user by ID
- `POST /user` – Create new user
- `PUT /user/{id}` – Update user
- `PATCH /user/update/username/{id}` – Update user username for a given user record.
- `PATCH /user/update/password/{id}` – Update user password for a given user record.
- `PATCH /user/update/role/{id}` – Update user role for a given user record.
- `DELETE /user/{id}` – Soft delete (disable) user

---

### 🧑‍💼 `EmployeeController`
- `GET /employee` – Get all employee data
- `GET /employee/{id}` – Get employee data by ID
- `POST /employee` – Register employee data for current user
- `PUT /employee/{id}` – Update employee
- `PATCH /employee/update/name/{id}` – Update employee name for a given employee record.
- `PATCH /employee/update/email/{id}` – Update employee email for a given employee record.
- `PATCH /employee/update/position/{id}` – Update employee position for a given employee record.
- `DELETE /employee/{id}` – Delete employee

---

### ⏱️ `AssistanceController`
- `GET /assistance/{employeeId}` – Get all assistances for a specific employee
- `GET /assistance/date/{date}` – Get all assistances on a given date
- `GET /assistance/between/{startDate}/{finishDate}` – Get assistances between two dates
- `POST /assistance` – Register a new assistance (entry)
- `PATCH /assistance/departure/{id}` – Update departure time for a given assistance record
- `PATCH /assistance/update/date/{id}` - Update date for a given assistance record
- `PATCH /assistance/update/entry-time/{id}` - Update entry time for a given assistance record
- `DELETE /assistance/{id}` – Delete a given assistance record

---

## 🧪 Testing

This project includes automated tests to ensure the stability and correctness of core features:

- ✅ **Unit tests** for the service layer using JUnit 5 and Mockito.
- ✅ **Integration tests** for the repository layer using H2 in-memory database and `@DataJpaTest`.

---

## 📈 Future Improvements

- Add password recovery flow
- Partial field updates (PATCH) for all models (✅07/09/25)
- Custom attendance reports in PDF/Excel
- Structured error messages with HTTP statuses and codes

---

## 📌 License

This project is open-source and free to use for educational and commercial purposes.
