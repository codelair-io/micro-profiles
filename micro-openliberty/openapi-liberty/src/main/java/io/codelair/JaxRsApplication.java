package io.codelair;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.OAuthScope;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Jax-RS application entry-point
 * <p>
 * Bugs: none known
 *
 * @author Hassan Nazar, hassenasse @ github (2019)
 */
@OpenAPIDefinition( info = @Info( title = "Fruits API",
        version = "1.0.0",
        contact = @Contact( name = "Redbridge Technology AB",
                email = "hassan.nazar@redbridge.se",
                url = "https://www.redbridge.se/" ) ) )
@SecurityScheme( securitySchemeName = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        description = "Authentication needed for this operation",
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow( authorizationUrl = "https://example.com/api/oauth/dialog",
                        tokenUrl = "https://example.com/api/oauth/token",
                        scopes = { @OAuthScope( name = "get:fruits" ),
                                @OAuthScope( name = "post:fruits" ) } ) )
)
@ApplicationPath( "/" )
public class JaxRsApplication extends Application
{
}
