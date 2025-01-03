# Calendar Log Backend

## Overview

The Calendar Log Backend is a Spring Boot-based application designed to handle all backend operations for the Calendar Log project. It serves as the API layer for handling user authentication, managing companies, and storing communication logs. The backend integrates with MongoDB for persistent storage and provides services for users and administrators to interact with the system. It uses token-based authentication and BCrypt for password encryption.

## Features

- **User Authentication**: Manages user registration, login, and token-based authentication. Passwords are encrypted using **BCrypt**.
- **Company Management**: Admins can manage companies and communication methods.
- **Communication Logs**: Stores and manages communication logs for each company.
- **API Endpoints**: Exposes RESTful APIs for the frontend to interact with.
- **Service Layer**: Handles business logic for communication methods, companies, and users.
- **Token-Based Authentication**: JWT tokens are used for secure API access.
- **Database Integration**: Uses **MongoDB** as the database to store company data, communication logs, and user details.
- **Deployed on Render.com**: The backend is deployed on Render for easy access and scalability.

## Technologies Used

- **Spring Boot**: For building the backend API.
- **Spring Data MongoDB**: For database integration with MongoDB.
- **Spring Security**: For managing authentication and authorization with token-based authentication.
- **BCrypt**: For password encryption and secure storage.
- **JWT**: For generating and validating JSON Web Tokens for user authentication.
- **Render.com**: For deploying the backend application.

## Project Structure

```plaintext
.vscode/
└── [Configuration for IDE]
main/
├── java/com/jayx/CalendarLog/
│   ├── config/
│   │   └── [Configuration classes]
│   ├── models/
│   │   ├── CommunicationLog.java
│   │   ├── CommunicationMethod.java
│   │   ├── Company.java
│   │   ├── Token.java
│   │   └── User.java
│   ├── repositories/
│   │   ├── CommunicationLogRepo.java
│   │   ├── CommunicationMethodRepo.java
│   │   ├── CompanyRepo.java
│   │   ├── TokenRepository.java
│   │   └── UserRepository.java
│   ├── services/
│   │   ├── CommunicationLogService.java
│   │   ├── CommunicationMethodService.java
│   │   ├── CompanyService.java
│   │   ├── NotificationService.java
│   │   ├── TokenService.java
│   │   └── UserService.java
│   ├── CalendarLogApplication.java
│   └── Controller.java
└── resources/
    ├── application.properties
    └── [Other resource files]

```

## Installation
To get started with the backend, follow these steps:

**1. Clone the repository**
Open your terminal and run the following command to clone the repository:


git clone https://github.com/jayakrishnachandra/CalendarLog.git


**2. Navigate to the project directory**
After cloning, go into the project directory:


cd CalendarLog


**3. Install dependencies**
This project uses Maven for dependency management. If Maven is installed, you can install the necessary dependencies by running:


mvn clean install


**4. Configure application properties**
In the src/main/resources/application.properties file, configure your MongoDB connection and other necessary settings. For example:

properties
spring.data.mongodb.uri=mongodb://yourMongoDBURI
spring.mongodb.database=calendarlog
server.port=8080


**5. Start the application**


Run the following command to start the Spring Boot application:

mvn spring-boot:run

The backend will be available at http://localhost:8080.

# Features


**BCrypt Password Encoder**
The application uses BCrypt to encrypt passwords before storing them in the database. When a user registers or logs in, their password is hashed using BCrypt to ensure security.

**Token-Based Authentication**
Tokens are used to authenticate users. When a user logs in, the backend generates a UUID token that can be used to access protected API endpoints. The token is passed in the HTTP request header as Authorization: Bearer <token>.

**MongoDB Integration**
The backend uses MongoDB to store user data, company information, and communication logs. MongoDB is chosen for its flexibility and scalability, making it ideal for handling dynamic data.

**Deployed on Render.com**

