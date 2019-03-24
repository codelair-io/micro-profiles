package io.codelair.jwt.endpoint;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secure")
public class SecureResource {

    @Inject
    JsonWebToken jwtPrinciple;

    @GET
    @DenyAll
    @Path("denyall")
    public Response get() {
        return Response.ok("Hello " + jwtPrinciple.getName()).build();
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("allowIfAuth")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAuth() {
        return Response.ok("Hello " + jwtPrinciple.getName()).build();
    }

}
