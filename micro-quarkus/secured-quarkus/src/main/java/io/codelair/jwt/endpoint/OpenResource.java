package io.codelair.jwt.endpoint;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/open")
public class OpenResource {

    @GET
    @PermitAll
    public Response get(){
        return Response.ok("Hello Sir").build();
    }
}
