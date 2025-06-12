
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
- `DELETE /user/{id}` – Soft delete (disable) user

---

### 🧑‍💼 `EmployeeController`
- `GET /employee` – Get all employee data
- `GET /employee/{id}` – Get employee data by ID
- `POST /employee` – Register employee data for current user
- `PUT /employee/{id}` – Update employee
- `DELETE /employee/{id}` – Delete employee

---

### ⏱️ `AssistanceController`
- `GET /assistance/{employeeId}` – Get all assistances for a specific employee
- `GET /assistance/date/{date}` – Get all assistances on a given date
- `GET /assistance/between/{startDate}/{finishDate}` – Get assistances between two dates
- `POST /assistance` – Register a new assistance (entry)
- `PATCH /assistance/departure/{id}` – Update departure time for a given assistance record

---

## 📈 Future Improvements

- Add password recovery flow
- Partial field updates (PATCH) for all models
- Custom attendance reports in PDF/Excel
- Structured error messages with HTTP statuses and codes

---


## 📌 License

This project is open-source and free to use for educational and commercial purposes.
