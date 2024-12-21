package org.rohit.test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.rohit.entity.Product;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class ProductResourceTest{

    @Test
    public void testGetAllProducts() {
        RestAssured.given()
                .when().get("/product")
                .then().statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Apple Mac Book");
        product.setDescription("This is a Apple Mac Book");
        product.setPrice(130000.00);
        product.setQuantity(5);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .when().post("/product")
                .then().statusCode(201)
                .body("name", equalTo("Apple Mac Book"))
                .body("description", equalTo("This is a Apple Mac Book"))
                .body("price", equalTo(130000.00F))
                .body("quantity", equalTo(5));
    }

    @Test
    public void testGetProductById() {
        int productId = 1;
        RestAssured.given()
                .when().get("/product/" + productId)
                .then().statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testDeleteProduct() {
        int productId = 2;
        RestAssured.given()
                .when().delete("/product/" + productId)
                .then() .statusCode(204);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(3);
        product.setName("Apple iPhone 15 Pro 128 GB");
        product.setDescription("Apple iPhone 15 Pro 128 GB is a new iPhone, assembled in India.");
        product.setPrice(120000.00);
        product.setQuantity(20);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .when().put("/product")
                .then() .statusCode(200)
                .body("name", equalTo("Apple iPhone 15 Pro 128 GB"))
                .body("description", equalTo("Apple iPhone 15 Pro 128 GB is a new iPhone, assembled in India."))
                .body("price", equalTo(120000.00F))
                .body("quantity", equalTo(20));
    }

    @Test
    public void testGetAllProductswithSortByPrice() {
        RestAssured.given()
                .when().get("/product/sortbyprice")
                .then() .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testGetProductQuantitybyId() {
        int productId = 1;
        int quantity = 10;
        RestAssured.given()
                .when().get("/product/quantity/" + productId + "/" + quantity)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }


}
