# Car Care Services 

> **A scalable Car Care Service platform backend built with Spring Boot, featuring role-based access, booking workflows, and real-time notifications.**

---

## Table of Contents
- Features
- Tech Stack
- Architecture
- Project Structure
- API Endpoints
- Setup & Installation
- Postman Collection
- Contributors

---

## Features
- **Role-Based Access Control**: Admin, Provider, Customer
- **Authentication & Authorization**: JWT
- **Service Management**: Add, update, delete, and search services
- **Booking System**: Create, track, and cancel service requests
- **Notifications**: Broadcast and targeted alerts
- **Reviews & Ratings**: Manage customer feedback
- **Admin Dashboard APIs**: User management, audit logs, settings

---

## Tech Stack
- **Backend**: Java 21, Spring Boot 3.x
- **Database**: MySQL
- **Authentication**: JWT
- **Tools**: Postman, IntelliJ IDEA, Jira
- **API Testing**: Postman Collection included

---

## Architecture
```
Controller → Service → Repository → MySQL
Authentication → OAuth2 + JWT
```

---

## Project Structure

```
src/main/java/org/os/carcareservice/
├── config/         # Configuration classes
├── controller/     # REST Controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA Entities
├── exception/      # Custom exceptions
├── mappers/        # Object mappers
├── repository/     # Data access layer
├── service/        # Business logic
└── specification/  # JPA Specifications

```

---


## API Endpoints

### Authentication (`/api/auth`)
- `POST /api/auth/register/admin` - Register a new admin
- `POST /api/auth/register/customer` - Register a new customer
- `POST /api/auth/register/provider` - Register a new service provider
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh-token` - Refresh access token

### Users (`/api/users`)
- `GET /api/users/profile` - Get current user profile
- `PATCH /api/users/profile` - Update current user profile
- `PATCH /api/users/password` - Change password
- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user by ID
- `PATCH /api/users/{id}/role` - Update user role (Admin only)
- `DELETE /api/users/{id}` - Delete user (Admin only)

### Services (`/api/services`)
- `GET /api/services` - Get all services
- `GET /api/services/{id}` - Get service by ID
- `POST /api/services` - Create new service (Provider only)
- `PUT /api/services/{id}` - Update service (Provider only)
- `DELETE /api/services/{id}` - Delete service (Provider only)

### Service Requests (`/api/requests`)
- `POST /api/requests` - Create new service request (Customer only)
- `GET /api/requests/{id}` - Get request by ID
- `GET /api/requests/customer` - Get current customer's requests
- `GET /api/requests/provider` - Get requests for current provider
- `PATCH /api/requests/{id}/status` - Update request status
- `GET /api/requests/{id}/history` - Get request status history

### Customer Cars (`/api/customer-cars`)
- `GET /api/customer-cars` - Get all customer cars
- `GET /api/customer-cars/{id}` - Get customer car by ID
- `POST /api/customer-cars` - Add new car (Customer only)
- `PUT /api/customer-cars/{id}` - Update car details (Owner only)
- `DELETE /api/customer-cars/{id}` - Delete car (Owner only)

### Providers (`/api/providers`)
- `GET /api/providers` - Get all service providers
- `GET /api/providers/{id}` - Get provider by ID
- `GET /api/providers/{id}/services` - Get services by provider
- `GET /api/providers/{id}/reviews` - Get provider reviews

### Reviews (`/api/reviews`)
- `POST /api/reviews` - Add new review (Customer only)
- `GET /api/reviews/provider/{providerId}` - Get reviews for a provider
- `GET /api/reviews/customer` - Get current user's reviews
- `PUT /api/reviews/{id}` - Update review (Owner only)
- `DELETE /api/reviews/{id}` - Delete review (Owner/Admin only)

### Notifications (`/api/notifications`)
- `GET /api/notifications` - Get current user's notifications
- `GET /api/notifications/unread-count` - Get unread notifications count
- `PATCH /api/notifications/{id}/read` - Mark notification as read
- `PATCH /api/notifications/read-all` - Mark all notifications as read

---

## Setup & Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Nada-ibrahim12/CarCareService
   cd CarCareService
   ```

2. Configure the environment:
   - Create a `.env` file in the project root directory
   - Copy the contents from `.env.example` to `.env`
   - Update the following variables in the `.env` file:
     ```
     DB_URL=your_database_url
     DB_USERNAME=your_mysql_username
     DB_PASSWORD=your_mysql_password
     API_PORT=your_api_port
     JWT_SECRET=your_jwt_secret_key_here
     JWT_EXPIRATION=your_jwt_expiration
     ```
   - Replace `your_database_url`, `your_mysql_username`, `your_mysql_password`, `your_api_port`, `your_jwt_secret_key_here` and `your_jwt_expiration` with your actual values
   - Make sure MySQL is running and the specified database exists

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

---

## Postman Collection
[Postman Collection Link](https://drive.google.com/file/d/1kZak3x1GobIPMg9QW2Ffrz1-zgJxthIi/view)


---

## Contributors
- **Nada Ibrahim** – Team Lead & Backend Developer | 
  [LinkedIn Profile](https://www.linkedin.com/in/nada-ibrahim-70930725a)
- **Ephraim Youssef** – Backend Developer | 
  [GitHub Profile](https://github.com/EphraimYoussef) | 
  [LinkedIn Profile](https://www.linkedin.com/in/ephraimyoussef/)
- **Abdelrahman Kadry** – Backend Developer | 
  [GitHub Profile](https://github.com/Kadry-jr) | 
  [LinkedIn Profile](https://www.linkedin.com/in/abdel-rahman-kadry/)
- **Roqaia Hassan** – Backend Developer
- **Malak** – Backend Developer 
- **Salma** – Backend Developer 
- **Mohamed** – Backend Developer 
