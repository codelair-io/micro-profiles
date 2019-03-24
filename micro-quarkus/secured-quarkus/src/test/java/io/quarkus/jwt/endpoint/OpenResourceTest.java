package io.quarkus.jwt.endpoint;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OpenResourceTest {

    @Test
    public void shouldReturn200FromOpenEP(){
        given()
                .when().get("/open")
                .then()
                    .statusCode(200)
                    .body(is("Hello Sir"));
    }
}
