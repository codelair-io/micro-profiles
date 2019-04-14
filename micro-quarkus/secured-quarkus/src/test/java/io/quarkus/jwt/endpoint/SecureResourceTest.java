package io.quarkus.jwt.endpoint;

import io.codelair.jwt.util.OidcClient;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class SecureResourceTest {

    private JsonObject token;

    @BeforeEach
    public void setupTest(){

    }

    @Test
    public void shouldNotAllowDenyAllCalls(){
        Response response = given()
                .when().get("/secure/denyall")
                .thenReturn();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

    @Test
    public void shouldAllowCallWithJWT(){
        //given
        var client = OidcClient.Builder.newBuilder()
                .setClientId("admin")
                .setUsername("admin")
                .setPassword("admin")
                .setAuthUrl("http://localhost:4040/auth/realms/master/protocol/openid-connect/auth")
                .setTokenUrl("http://localhost:4040/auth/realms/master/protocol/openid-connect/token")
                .setRedirectUri("")
                .build();

        this.token = client.performTokenCall(OidcClient.FetchMethod.DIRECT_ACCESS_GRANT);

        //when
        System.out.println("token = " + token.getString("access_token"));
        Response response =
                given()
                    .header("Authorization", "Bearer " + token.getString("access_token"))
                .when()
                    .get("/secure/allowIfAuth")
                .thenReturn();

        //then
        assertThat(response.getStatusCode())
                .isEqualTo(HttpURLConnection.HTTP_OK);
    }

}
