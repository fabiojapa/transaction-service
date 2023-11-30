# Transaction Service

## Overview
Transaction Service is a Spring Boot-based microservice designed to manage financial transactions. It provides a robust API for creating, retrieving, and managing transactions in a scalable and efficient manner.

## Features
- Create new transactions with various attributes such as account ID, amount, and operation type.
- Retrieve transaction details by ID.
- List all transactions with filtering and pagination support.
- Robust error handling and validation mechanisms.

## Technology Stack
- **Spring Boot**: For creating stand-alone, production-grade Spring-based applications.
- **PostgreSQL**: As the primary database for storing transaction data.
- **Docker**: For containerizing the application and ensuring consistency across various environments.
- **JUnit & Mockito**: For unit testing and ensuring code quality.

## Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.8 or later
- Docker, docker-compose
- PostgreSQL (optional for local setup)

### Running the Application

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/fabiojapa/transaction-service.git
   cd transaction-service

2. **Build the Application:**
```bash
mvn clean install
```

3. **Run Using Docker:**

```bash
docker-compose up --build
```
This will start the application along with the PostgreSQL database.

4. **Accessing the API:**

Once the application is running, the API can be accessed via http://localhost:8080/.

Use tools like Postman or Swagger to interact with the API.

### Postman
There is a file to be imported in Postman:
```
src/test/resources/postman/transactions.postman_collection.json
```


### API Documentation
API documentation can be accessed at http://localhost:8080/swagger-ui/index.html

### Development
#### Setting Up the Development Environment
Import the project into your favorite IDE as a Maven project.
Ensure Lombok is installed in your IDE for proper handling of boilerplate code.
Configure a local PostgreSQL database or use the provided Docker setup(docker-compose  up -d db-migration --build).

#### Running Tests
Execute the following command to run the unit tests:

```bash
mvn test
```

### Contribution

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

### Contact
Fabio Takeshi Sakamoto

fabiojapa@gmail.com

https://www.linkedin.com/in/fabiosaka/