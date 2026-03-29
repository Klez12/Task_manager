# Task Manager Application

## Tech Stack
- Frontend: React (Vite)
- Backend: Spring Boot
- Database: MySQL
- Authentication: JWT

## Features
- User Registration & Login
- Create Task
- View Tasks
- Update Task Status
- Delete Task
- Filter Tasks

## How to Run

### Backend
cd backend
mvn spring-boot:run

### Frontend
cd frontend
npm install
npm run dev

## API Endpoints

POST   /api/auth/register  
POST   /api/auth/login  
GET    /api/tasks  
POST   /api/tasks  
PUT    /api/tasks/{id}  
DELETE /api/tasks/{id}

## Notes
- JWT token is required for protected APIs