
```markdown
# HealthTrackerAPI

HealthTrackerAPI is a RESTful web service designed for managing user accounts, tracking health metrics, and providing functionalities like user registration, login, and health data management. The project is built using **Spring Boot** and uses **JPA** for database management.

## Features

- **User Management**: Register users, login, and retrieve user details.
- **Health Metric Management**: (Future feature) Allow users to track health metrics like weight, blood pressure, etc.
- **Simple Authentication**: Basic email and password-based login (can be extended with JWT or OAuth).

## Technologies Used

- **Java 17** (or later)
- **Spring Boot 2.x**
- **Spring Data JPA**
- **H2 Database** (In-memory database for development, can be replaced with PostgreSQL or MySQL)
- **Lombok** (for reducing boilerplate code)
- **Maven** (for dependency management)

## Setup

### Prerequisites

- **Java 17+**: Ensure that you have JDK 17 or higher installed.
- **Maven**: Make sure Maven is installed for managing dependencies and building the project.

### Steps to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/mahingaRodin/HealthTrackerAPI.git
   ```

2. Navigate to the project directory:
   ```bash
   cd HealthTrackerAPI
   ```

3. Build and run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

   The application should now be running on `http://localhost:8080`.

### API Endpoints

- **Register User**
    - **POST** `/api/users/register`
    - Request body (JSON):
      ```json
      {
          "username": "john_doe",
          "email": "john@example.com",
          "password": "password123"
      }
      ```

- **Login User**
    - **POST** `/api/users/login`
    - Request body (JSON):
      ```json
      {
          "email": "john@example.com",
          "password": "password123"
      }
      ```

- **Get User by ID**
    - **GET** `/api/users/{id}`

### Database Configuration

By default, the project uses an in-memory H2 database. If you wish to use a different database like MySQL or PostgreSQL, update the `application.properties` file.

### Testing

You can test the API endpoints using tools like **Postman** or **cURL**.

---

## Contributing

If you'd like to contribute to this project, feel free to fork it, make your changes, and submit a pull request.

---

## Contact

For any inquiries, you can reach me via email at [mahingarodin@gmail.com](mailto:mahingarodin@gmail.com).

---

Feel free to modify any sections as needed!