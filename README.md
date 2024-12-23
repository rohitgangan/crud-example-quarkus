# crud-example-quarkus
# Quarkus Reactive REST API with PostgreSQL

This project is a Quarkus application that demonstrates how to build a reactive REST API using Hibernate Reactive and PostgreSQL. This project uses Repository Pattern

## Prerequisites

- Java 21 or higher
- Apache Maven 3.6.3 or higher
- PostgreSQL database

## Getting Started

### Setting Up the Database

1. Create a PostgreSQL database:
    ```sh
    psql -U postgres -c "CREATE DATABASE mydatabase;"
    psql -U postgres -c "CREATE USER myusername WITH PASSWORD 'mypassword';"
    psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE mydatabase TO myusername;"
    ```

2. Update the database configuration in `src/main/resources/application.properties`:
    ```properties
    quarkus.datasource.db-kind=postgresql
    quarkus.datasource.reactive.url=postgresql://localhost:5432/mydatabase
    quarkus.datasource.username=myusername
    quarkus.datasource.password=mypassword
    ```

### Running the Application

1. Start the application in development mode:
    ```sh
    ./mvnw quarkus:dev
    ```

### Building the Application

1. Build the application:
    ```sh
    ./mvnw clean package
    ```

2. Run the packaged application:
    ```sh
    java -jar target/crud-example-quarkus-dev.jar
    ```

## Endpoints

### Product Resource

- `GET /product`: Get all products.
- `POST /product`: Add a new product.
- `PUT /product`: Update an existing product.
- `DELETE /product/{id}`: Delete a product by ID.
- `GET /product/{id}`: Get a product by ID.
- `GET /product/sortbyprice`: Get all products sorted by price.
- `GET /product/quantity/{id}/{quantity}`: Check available product quantity by ID.

## Testing

### Running Tests

1. Run tests using Maven:
    ```sh
    ./mvnw test
    ```

### Example Test Case

Here's an example of a test case for adding a product:

```java
@Test
public void testAddProduct() {
    Product product = new Product();
    product.setName("Test Product");
    product.setDescription("This is a test product");
    product.setPrice(100.0);
    product.setQuantity(50);

    RestAssured.given()
        .contentType(ContentType.JSON)
        .body(product)
        .when().post("/product")
        .then()
        .statusCode(201)
        .body("name", equalTo("Test Product"))
        .body("description", equalTo("This is a test product"))
        .body("price", equalTo(100.0f))
        .body("quantity", equalTo(50));
}

