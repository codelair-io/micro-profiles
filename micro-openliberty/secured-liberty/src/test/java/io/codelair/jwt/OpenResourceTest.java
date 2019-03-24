package io.codelair.jwt;

import jwt.endpoint.JaxRsApplication;
import jwt.endpoint.OpenResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class OpenResourceTest extends AbstractRunner{

    @Deployment
    public static WebArchive createDeployment() {
        return createBaseDeployment()
                .addClasses(
                        JaxRsApplication.class,
                        OpenResource.class
                );
    }

    @Test
    public void openEndpointShouldAllowEntrance(@ArquillianResource URL contextPath){
        var resp = given()
            .when()
                .get(contextPath + "open")
            .thenReturn();

        assertThat(resp.getStatusCode())
                .isEqualTo(200);
    }

}

