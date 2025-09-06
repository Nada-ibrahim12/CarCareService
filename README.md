# 🚗 CarCareService – Backend API  
![Java](https://img.shields.io/badge/Java-17-orange) !Spring Boot !MySQL !OAuth2 !License

> **A scalable Car Care Service platform backend built with Spring Boot, featuring role-based access, booking workflows, and real-time notifications.**

---

## 📌 Table of Contents
- Features
- Tech Stack
- Architecture
- API Endpoints
- Setup & Installation
- Postman Collection
- Contributors

---

## ✅ Features
- **Role-Based Access Control**: Admin, Provider, Customer
- **Authentication & Authorization**: OAuth2 + JWT
- **Service Management**: Add, update, delete, and search services
- **Booking System**: Create, track, and cancel service requests
- **Notifications**: Broadcast and targeted alerts
- **Reviews & Ratings**: Manage customer feedback
- **Admin Dashboard APIs**: User management, audit logs, settings

---

## 🛠 Tech Stack
- **Backend**: Java 17, Spring Boot 3.x
- **Database**: MySQL
- **Authentication**: OAuth2, JWT
- **Tools**: Postman, IntelliJ IDEA, Jira
- **API Testing**: Postman Collection included

---

## 🏗 Architecture
```
Controller → Service → Repository → MySQL
Authentication → OAuth2 + JWT
```

---

## 🔗 API Endpoints
<details>
<summary>Authentication</summary>

- `POST /auth/register/admin` – Register Admin  
- `POST /auth/register/customer` – Register Customer  
- `POST /auth/register/provider` – Register Provider  
- `POST /auth/login` – Login  
</details>

<details>
<summary>Bookings & Requests</summary>

- `POST /requests` – Create Service Request  
- `GET /requests/{id}` – Get Request by ID  
- `DELETE /requests/{id}` – Cancel Request  
</details>

<details>
<summary>Notifications</summary>

- `POST /notifications/broadcast` – Broadcast Notification  
- `GET /notifications/user/{id}` – Get User Notifications  
</details>

*(Full API list in Postman Collection)*

---

## ⚡ Setup & Installation
```bash
# Clone the repository
git clone https://github.com/Nada-ibrahim12/CarCareService.git

# Navigate to project folder
cd CarCareService

# Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/carcare
spring.datasource.username=root
spring.datasource.password=yourpassword

# Run the application
mvn spring-boot:run
```

---

## 📂 Postman Collection
[Postman Collection Link](https://drive.google.com/file/d/1kZak3x1GobIPMg9QW2Ffrz1-zgJxthIi/view)


---

## 👩‍💻 Contributors
- **Nada Ibrahim** – Team Lead & Backend Developer  
  [LinkedIn Profile](https://www.linkedin.com/in/nada-ibrahim-70930725a)
- **Ephraim Youssef** – Backend Developer
  [GitHub Profile](https://github.com/EphraimYoussef)
  [LinkedIn Profile](https://www.linkedin.com/in/ephraimyoussef/)
- **Abdelrahman Kadry** – Backend Developer
  [GitHub Profile](https://github.com/Kadry-jr)
  [LinkedIn Profile](https://www.linkedin.com/in/abdel-rahman-kadry/)
- **Roqaia Hassan** – Backend Developer
- **Malak** – Backend Developer 
- **Salma** – Backend Developer 
- **Mohamed** – Backend Developer 

---
