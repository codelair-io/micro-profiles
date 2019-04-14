package io.codelair.jwt;

import jwt.endpoint.JaxRsApplication;
import jwt.endpoint.SecureResource;
import jwt.util.OidcClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.json.JsonObject;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class SecureResourceTest extends AbstractRunner{

    @Deployment
    public static WebArchive createDeployment() {
        return createBaseDeployment()
                .addClasses(
                        JaxRsApplication.class,
                        SecureResource.class,
                        OidcClient.class
                );
    }

    private JsonObject token;

    @Before
    public void setupTest(){
        var oidcClient = OidcClient.Builder.newBuilder()
                .setClientId("admin-cli")
                .setUsername("admin")
                .setPassword("admin")
                .setRedirectUri("")
                .setAuthUrl("http://localhost:4040/auth/realms/master/protocol/openid-connect/auth")
                .setTokenUrl("http://localhost:4040/auth/realms/master/protocol/openid-connect/token")
                .build();

        token = oidcClient.performTokenCall(OidcClient.FetchMethod.DIRECT_ACCESS_GRANT);
    }

    @Test
    public void shouldFailForNonAuthUser(@ArquillianResource URL contextPath){
        var resp = given()
            .when()
                .get(contextPath + "secure/denyall")
            .thenReturn();

        assertThat(resp.getStatusCode())
                .isEqualTo(HttpURLConnection.HTTP_FORBIDDEN);
    }

    @Test
    public void shouldAllowWithAuth(@ArquillianResource URL contextPath){
        System.out.println("token = " + token.getString("access_token"));
        var resp =
                given()
                    .header("Authorization", "Bearer " + token.getString("access_token"))
                .when()
                    .get(contextPath + "secure/allowIfAuth")
                    .thenReturn();

        assertThat(resp.getStatusCode())
                .isEqualTo(HttpURLConnection.HTTP_OK);
    }

}
