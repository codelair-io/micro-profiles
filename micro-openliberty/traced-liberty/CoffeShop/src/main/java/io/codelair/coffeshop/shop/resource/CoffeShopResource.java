package io.codelair.coffeshop.shop.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/shop")
public class CoffeShopResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/espresso")
    public Response getEspresso(){
        return Response.ok("{\"coffeType\": \"espresso\"}").build();
    }

}
