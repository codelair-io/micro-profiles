package jwt.endpoint;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/")
@DeclareRoles({"ADMIN", "USER"})
public class JaxRsApplication extends Application {
}
