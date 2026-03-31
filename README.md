# Task Management System (Full Stack Project)

## Overview

This is a full-stack Task Management application built using React, Spring Boot, and MySQL. It allows users to create, assign, and track tasks with role-based access control.

---

## Features

### Authentication

* User login using JWT
* Secure APIs with Spring Security
* Password hashing using BCrypt

### User Roles

* **Admin**

  * View all users
  * Manage all tasks
* **User**

  * Create tasks
  * Update assigned tasks
  * View dashboard

### Task Management

* Create, update, and delete tasks
* Assign tasks to users
* Update task status (TODO, IN_PROGRESS, DONE)
* Filter tasks by status

### Dashboard

* View all tasks
* Filter tasks by status
* View assigned user
* Mark tasks as completed

---

## Tech Stack

| Layer    | Technology                        |
| -------- | --------------------------------- |
| Frontend | React (Vite), Axios               |
| Backend  | Spring Boot, Spring Security, JPA |
| Database | MySQL                             |
| DevOps   | Docker, Docker Compose            |
| Auth     | JWT                               |

---

## Project Structure

```
Task_manager/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── security/
│
├── frontend/
│   ├── src/
│   │   ├── pages/
│   │   └── App.jsx
│
└── docker-compose.yml
```

---

## Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/Klez12/Task_manager.git
cd task-manager
```

---

### 2. Backend Setup

```bash
cd backend
.\mvnw.cmd clean install
.\mvnw spring-boot:run
```

Backend runs on:
http://localhost:8080

---

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:
http://localhost:5173

---

### 4. Database Setup

Create database in MySQL:

```sql
CREATE DATABASE taskdb;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

---
### 5. ## Demo Credentials

Use the following credentials to test the application:

**Admin User**

* Email: [admin@test.com](mailto:admin@test.com)
* Password: admin123

**Normal User**

* Email: [user@test.com](mailto:user@test.com)
* Password: user123

> Note: These are sample users created for testing purposes only.

## Docker Setup

Run the application using Docker:

```bash
docker-compose up --build
```

---

## API Endpoints

### Authentication

* POST /api/auth/login

### Users

* GET /api/users

### Tasks

* POST /api/tasks
* GET /api/tasks
* PUT /api/tasks/{id}
* DELETE /api/tasks/{id}

---

## Security

* JWT-based authentication
* Role-based authorization
* Protected APIs
* CORS configuration enabled

---

## Future Improvements

* Pagination and sorting
* Search functionality
* Task priority and due dates
* Cloud deployment

---

## Author

https://github.com/Klez12

---

## Status

Completed core features. Ready for submission.
