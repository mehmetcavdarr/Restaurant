# Restaurant Management System

This is a Spring Boot-based REST API project simulating a simple restaurant management system.

## Features

- Manage menus (add, update, delete dishes)
- Place and track orders
- RESTful endpoints using Spring Boot
- Uses Spring Data JPA for persistence
- H2 Database for development

## Technologies Used

- Java 17
- Spring Boot 3.4.1
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Maven

## How to Run

1. Make sure you have Java 17 and Maven installed.
2. Clone or download this repository.
3. Navigate to the project folder and run:

```bash
./mvnw spring-boot:run
```

4. The API will be accessible at `http://localhost:8080`

## Project Structure

- `Controller/`: Handles REST API endpoints
- `Model/`: Contains JPA entity classes
- `Repository/`: JPA repositories for data access
- `Service/`: Business logic layer (if implemented)
- `resources/application.properties`: Configuration for database and app settings

## Authors

- [Mehmet Çavdar](https://github.com/mehmetcavdarr)
- [Salih Efehan Demir](https://github.com/SalihEfehanDemir)
- [İbrahim Bayır](https://github.com/ibrahimbayir)

## License

This project is for educational purposes.
