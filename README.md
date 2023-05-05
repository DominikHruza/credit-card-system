# Credit Card Application API

## Prerequisites

- Docker Compose

## Usage

1. Start the services:

   After cloning the project: 
    ```
   cd docker
   ```
   ```
   docker-compose up -d
   ```

2. Check the status of the services:

   ```
   docker-compose ps
   ```

   The output should show the status of the `credit-cards-db` and `credit-card-application-api` services.



3. Access the Swagger documentation:

   ```
   http://localhost:8080/swagger-ui/index.html
   ```

   This URL will take you to the Swagger documentation for the Credit Card Application API, where you can explore and test the available endpoints.

## Services

### credit-cards-db

This service runs a PostgreSQL database for storing credit card data.
Username: postgres, 
Password: postgres
### credit-card-application-api

This service runs the Credit Card Application API with default configs:

- Ports: Exposes port `8080`.
- Environment Variables:
    - `SPRING_DATASOURCE_URL`: JDBC URL for the PostgreSQL database.
    - `SPRING_DATASOURCE_USERNAME`: Username for connecting to the PostgreSQL database.
    - `SPRING_DATASOURCE_PASSWORD`: Password for connecting to the PostgreSQL database.
